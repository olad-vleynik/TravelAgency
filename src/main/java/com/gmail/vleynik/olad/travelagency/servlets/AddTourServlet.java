package com.gmail.vleynik.olad.travelagency.servlets;

import com.gmail.vleynik.olad.travelagency.dao.TourDAO;
import com.gmail.vleynik.olad.travelagency.dao.builders.TourBuilder;
import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;
import com.gmail.vleynik.olad.travelagency.services.UserInputCheckService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@WebServlet("/add_tour")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 20)   // 20MB
public class AddTourServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddTourServlet.class);

    private static final String NEW_TOUR_JSP = "/WEB-INF/jsp/newTour.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = "";
        Tour tour;

        String tourName = request.getParameter("tourName");
        String tourType = request.getParameter("tourType");
        String info = request.getParameter("info");
        String country = request.getParameter("country");
        String departure = request.getParameter("departure");
        String transfer = request.getParameter("transfer");
        String hotelName = request.getParameter("hotelName");
        String rating = request.getParameter("rating");
        String nights = request.getParameter("nights");
        String persons = request.getParameter("persons");
        String price = request.getParameter("price");
        String hot = request.getParameter("hot");

        if (UserInputCheckService.isAllFilled(tourName, tourType, info, departure, transfer, hotelName, rating, nights, price)) {
            TourDAO tourDAO = new TourDAO();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

            tour = new TourBuilder()
                    .setName(tourName)
                    .setType(Tour.TourType.valueOf(tourType.toUpperCase(Locale.ROOT)))
                    .setInfo(info)
                    .setCountry(country)
                    .setDate(LocalDate.parse(departure, dateFormatter))
                    .setTransferType(Tour.TransferType.valueOf(transfer.toUpperCase(Locale.ROOT)))
                    .setHotelName(hotelName)
                    .setHot(false)
                    .setHotelRating(Integer.parseInt(rating.substring(0, 1)))
                    .setNightsCount(Integer.parseInt(nights))
                    .setPersonsCount(Integer.parseInt(persons))
                    .setCostInUSD(Integer.parseInt(price))
                    .setPreviewFile("")
                    .build();

            if (hot != null)
                tour.setHot(true);

            if (request.getPart("previewFile") != null) {
                try {
                    File imagesDirectory = new File(request.getSession().getServletContext().getRealPath("/images"));
                    if (!imagesDirectory.exists()) {
                        imagesDirectory.mkdirs();
                    }

                    Part filePart = request.getPart("previewFile");
                    fileName = randomString() + filePart.getSubmittedFileName();
                    if (fileName.length() > 11) {
                        filePart.write(imagesDirectory.getAbsolutePath() + "\\" + fileName);
                        tour.setPreviewFile(fileName);
                    } else {
                        tour.setPreviewFile("no-image.jpg");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("can't upload image file");
                }
            } else {
                tour.setPreviewFile("no-image.jpg");
            }

            tour.setId(tourDAO.addNew(tour));

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(String.valueOf(tour.getId()));
        } else {
            log.error("got not fulfilled post request");
            response.sendError(503);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(NEW_TOUR_JSP).forward(request, response);
    }

    private String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        buffer.append("-");

        return buffer.toString();
    }
}
