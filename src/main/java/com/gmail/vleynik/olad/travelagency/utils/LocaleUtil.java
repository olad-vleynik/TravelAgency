package com.gmail.vleynik.olad.travelagency.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Used to define users locale using cookies or browser locales. Also, setups locale cookie
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
public class LocaleUtil {

    //one year
    private static final int COOKIE_AGE = 60 * 60 * 24 * 365;

    /**
     * Private constructor excludes creation object of utility class
     *
     * @throws IllegalStateException on calling
     */
    private LocaleUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Provides locale from browser locales
     *
     * @return "ru" if browser locales contains "ru" locale, or "en" if not
     */
    public static String getLocaleFromBrowserLocales(ServletRequest request) {
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (locale.toLanguageTag().equals("ru")) {
                return "ru";
            }
        }
        return "en";
    }

    /**
     * Provides locale from client cookies
     *
     * @return locale if cookie named "locale" found, or null if not
     */
    public static String getLocaleFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("locale".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Sets client locale cookie
     *
     * @param response - http response to set cookie
     * @param locale   - locale to set
     */
    public static void addLocaleCookie(HttpServletResponse response, String locale) {
        Cookie localeCookie = new Cookie("locale", locale);
        localeCookie.setPath("/");
        localeCookie.setDomain("vlad-travel.pp.ua");
        localeCookie.setMaxAge(COOKIE_AGE);
        response.addCookie(localeCookie);
    }
}
