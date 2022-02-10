package com.gmail.vleynik.olad.travelagency.servlets;

import com.gmail.vleynik.olad.travelagency.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/tour")
public class TourServiceServlet extends HttpServlet {

    private static final String NEW_TOUR_JSP = "/WEB-INF/jsp/newTour.jsp";
    private static final String TOUR_DETAILS_JSP = "/WEB-INF/jsp/tourDetails.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (request.getParameter("id") != null) {
            //TODO id exists check, setAttribute to tour, numeric check
            request.getRequestDispatcher(TOUR_DETAILS_JSP).forward(request, response);
        } else if (request.getParameter("action").equals("add") && isAdminOrManager(session)) {
            request.getRequestDispatcher(NEW_TOUR_JSP).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    private boolean isAdminOrManager(HttpSession session) {
        return session.getAttribute("user_access_level") == User.AccessLevel.ADMINISTRATOR
                || session.getAttribute("user_access_level") == User.AccessLevel.MANAGER;
    }
}
