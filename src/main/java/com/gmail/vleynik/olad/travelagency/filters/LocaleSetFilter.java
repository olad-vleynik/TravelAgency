package com.gmail.vleynik.olad.travelagency.filters;

import com.gmail.vleynik.olad.travelagency.utils.LocaleUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to set client locale on session creation
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
@WebFilter("/*")
public class LocaleSetFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();

        if (session.isNew()) {
            String locale = LocaleUtil.getLocaleFromCookies((HttpServletRequest) request);

            if (locale == null) {
                locale = LocaleUtil.getLocaleFromBrowserLocales(request);
                LocaleUtil.addLocaleCookie((HttpServletResponse) response, locale);
            }

            session.setAttribute("locale", locale);
        }

        next.doFilter(request, response);
    }
}