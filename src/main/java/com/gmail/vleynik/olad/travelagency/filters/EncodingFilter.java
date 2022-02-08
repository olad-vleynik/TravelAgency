package com.gmail.vleynik.olad.travelagency.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    private static final String ENCODING = "UTF-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        Enumeration locales = request.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = (Locale) locales.nextElement();
            //TODO
        }
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(ENCODING);
        }

        response.setContentType("text/html; charset=" + ENCODING);
        response.setCharacterEncoding(ENCODING);

        next.doFilter(request, response);
    }
}
