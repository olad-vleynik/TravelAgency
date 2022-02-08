package com.gmail.vleynik.olad.travelagency.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Used to set encoding
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
    private static final String ENCODING = "UTF-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(ENCODING);
        }

        response.setContentType("text/html; charset=" + ENCODING);
        response.setCharacterEncoding(ENCODING);

        next.doFilter(request, response);
    }
}
