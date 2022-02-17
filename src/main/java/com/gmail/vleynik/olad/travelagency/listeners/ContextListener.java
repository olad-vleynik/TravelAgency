package com.gmail.vleynik.olad.travelagency.listeners;

import com.gmail.vleynik.olad.travelagency.services.ConnectionService;
import com.gmail.vleynik.olad.travelagency.services.TaskExecutorService;
import com.gmail.vleynik.olad.travelagency.services.tasks.RemoveExpiredEntryTask;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.time.LocalTime;

/**
 * Used to log context state and initialize database connection pool
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);
    TaskExecutorService removeExpiredEntryExecutor =
            new TaskExecutorService(new RemoveExpiredEntryTask());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("start context initialization");

        try {
            ConnectionService.init();
            removeExpiredEntryExecutor.startExecutionAt(LocalTime.of(0, 0), 24);
            log.debug("application has been successfully initialized");
        } catch (IOException e) {
            log.fatal("application initialization failed");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        removeExpiredEntryExecutor.stop();
        AbandonedConnectionCleanupThread.checkedShutdown();
        log.debug("context destroyed");
    }
}
