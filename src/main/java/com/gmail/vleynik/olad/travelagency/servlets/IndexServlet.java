package com.gmail.vleynik.olad.travelagency.servlets;

import com.gmail.vleynik.olad.travelagency.dao.TourDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("")
public class IndexServlet  extends HttpServlet {
    private static final String INDEX_JSP = "/WEB-INF/jsp/index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TourDAO tourDAO = new TourDAO();
        List<Tour> hotTours = tourDAO.getTenHottest();
        List<Tour> nonHotTours = new ArrayList<>();

        if(hotTours.size() < 10) {
            nonHotTours = tourDAO.getNearestNonHot(10 - hotTours.size());
        }

        request.setAttribute("hotTours", hotTours);
        request.setAttribute("nonHotTours", nonHotTours);

        request.getRequestDispatcher(INDEX_JSP).forward(request, response);
    }
}
