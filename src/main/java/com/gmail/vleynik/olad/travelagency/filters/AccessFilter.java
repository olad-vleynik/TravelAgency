package com.gmail.vleynik.olad.travelagency.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws ServletException, IOException {

        next.doFilter(request, response);
    }
}
