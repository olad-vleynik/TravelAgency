package com.gmail.vleynik.olad.travelagency.servlets;

import com.gmail.vleynik.olad.travelagency.dao.TourDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/tour")
public class ShowTourServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ShowTourServlet.class);

    private static final String TOUR_DETAILS_JSP = "/WEB-INF/jsp/tourDetails.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (request.getParameter("id") != null) {
            try {
                TourDAO tourDAO = new TourDAO();
                Tour tour = tourDAO.getById(Integer.parseInt(request.getParameter("id")));

                if (tour.getId() == -1) {
                    response.sendError(404);
                    return;
                }

                String imageURL = request.getRequestURL().toString();
                imageURL = imageURL.substring(0, imageURL.lastIndexOf("/") + 1) + "images/" + tour.getPreviewFile();

                request.setAttribute("tourImagePath", imageURL);

                request.setAttribute("tour", tour);
                request.getRequestDispatcher(TOUR_DETAILS_JSP).forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("can't get tour from database");
                response.sendError(503);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
