package com.gmail.vleynik.olad.travelagency.filters;

import java.util.Arrays;
import java.util.List;

public class AccessFilterPaths {
    protected static final List<String> USER_PAGES;
    protected static final List<String> MANAGER_PAGES;
    protected static final List<String> ADMIN_PAGES;

    static {
        USER_PAGES = Arrays.asList("/order_tour", "/profile");

        MANAGER_PAGES = Arrays.asList("/edit_order", "/users", "/orders", "/edit_user");
        MANAGER_PAGES.addAll(USER_PAGES);

        ADMIN_PAGES = Arrays.asList("/add_tour", "/edit_tour", "/ban");
        ADMIN_PAGES.addAll(MANAGER_PAGES);
    }

    private AccessFilterPaths(){ throw new IllegalStateException();}
}
