package com.gmail.vleynik.olad.travelagency.listeners;

import com.gmail.vleynik.olad.travelagency.dao.SavedEntryDAO;
import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;
import com.gmail.vleynik.olad.travelagency.utils.TaskExecutorService;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
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
    TaskExecutorService removeAutoLoginIfCookieAgeExpiredTask =
            new TaskExecutorService(() -> {
                SavedEntryDAO savedEntryDAO = new SavedEntryDAO();
                savedEntryDAO.deleteExpiredSessions();
            });

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("start context initialization");

        try {
            ConnectionUtil.init();
            removeAutoLoginIfCookieAgeExpiredTask.startExecutionAt(LocalTime.of(0, 0), 24);
            log.debug("context has been successfully initialized");
        } catch (IOException e) {
            log.fatal(e.getMessage());
            log.fatal("context initialization failed");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        removeAutoLoginIfCookieAgeExpiredTask.stop();
        AbandonedConnectionCleanupThread.checkedShutdown();
        log.debug("context destroyed");
    }
}
