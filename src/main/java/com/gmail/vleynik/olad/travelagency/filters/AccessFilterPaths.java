package com.gmail.vleynik.olad.travelagency.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessFilterPaths {
    protected static final ArrayList<String> USER_PAGES;
    protected static final ArrayList<String> MANAGER_PAGES;
    protected static final ArrayList<String> ADMIN_PAGES;

    static {
        USER_PAGES = new ArrayList<>(Arrays.asList("/order_tour", "/profile"));

        MANAGER_PAGES = new ArrayList<>(Arrays.asList("/edit_order", "/cpanel", "/users", "/orders", "/edit_user"));
        MANAGER_PAGES.addAll(USER_PAGES);

        ADMIN_PAGES = new ArrayList<>(Arrays.asList("/add_tour", "/edit_tour", "/ban"));
        ADMIN_PAGES.addAll(MANAGER_PAGES);
    }

    private AccessFilterPaths() {
        throw new IllegalStateException();
    }
}
