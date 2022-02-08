package com.gmail.vleynik.olad.travelagency.listeners;

import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("start context initialization");
        try {
            ConnectionUtil.init();
            log.debug("context successfully initialized");
        } catch (IOException e) {
            log.fatal(e.getMessage());
            log.fatal("context not initialized");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AbandonedConnectionCleanupThread.checkedShutdown();
        log.debug("context destroyed");
    }
}
