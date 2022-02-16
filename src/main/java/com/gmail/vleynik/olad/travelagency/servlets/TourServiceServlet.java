package com.gmail.vleynik.olad.travelagency.servlets;

import com.gmail.vleynik.olad.travelagency.dao.TourDAO;
import com.gmail.vleynik.olad.travelagency.dao.builders.TourBuilder;
import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import com.gmail.vleynik.olad.travelagency.utils.UserInputCheck;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@WebServlet("/tour")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 20)   // 20MB
public class TourServiceServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    private static final String NEW_TOUR_JSP = "/WEB-INF/jsp/newTour.jsp";
    private static final String TOUR_DETAILS_JSP = "/WEB-INF/jsp/tourDetails.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String imagesPath;
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
        String price = request.getParameter("price");
        String hot = request.getParameter("hot");

        if(UserInputCheck.isAllFilled(tourName, tourType, info, departure, transfer, hotelName, rating, nights, price)){
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
                    .setCostInUSD(Integer.parseInt(price))
                    .setPreviewFile("")
                    .build();

            if (hot != null)
                tour.setHot(true);

            if (request.getPart("previewFile") != null) {

                try {
                    imagesPath = new File(".").getCanonicalPath() + File.separator + "tours_images" + File.separator;
                    File fileSaveDir = new File(imagesPath);
                    if (!fileSaveDir.exists()) {
                        fileSaveDir.mkdir();
                    }
                    Part filePart = request.getPart("previewFile");
                    fileName = randomString() + filePart.getSubmittedFileName();
                    if (fileName.length() > 11)
                        filePart.write(imagesPath + fileName);

                    tour.setPreviewFile(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("can't upload image file");
                }
            } else {
                tour.setPreviewFile("no-image.jpg");
            }

            tour.setId(tourDAO.addNew(tour));

            session.setAttribute("tourToView", tour);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(String.valueOf(tour.getId()));
        } else {
            log.error("got not fulfilled post request");
            response.sendError(503);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (request.getParameter("id") != null) {
            try {
                String imagesPath = new File(".").getCanonicalPath() + File.separator + "tours_images" + File.separator;
                TourDAO tourDAO = new TourDAO();
                Tour tour = tourDAO.getById(Integer.parseInt(request.getParameter("id")));

                if (tour.getId() == -1)
                    response.sendError(404);

                String tourImagePath = imagesPath + tour.getPreviewFile();

                if (new File(tourImagePath).exists()) {
                    String image = "file://" + imagesPath + tour.getPreviewFile();
                    request.setAttribute("tourImagePath", image.replaceAll("\\\\", "/"));
                } else {
                    String image = "file://" + imagesPath + "no-image.jpg";
                    request.setAttribute("tourImagePath", image.replaceAll("\\\\", "/"));
                }

                request.setAttribute("tour", tour);
                request.getRequestDispatcher(TOUR_DETAILS_JSP).forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("can't get tour from database");
                response.sendError(503);
            }
        } else if (request.getParameter("action").equals("add")
                && session.getAttribute("user_access_level") == User.AccessLevel.ADMINISTRATOR) {
            request.getRequestDispatcher(NEW_TOUR_JSP).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
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
