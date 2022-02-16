package com.gmail.vleynik.olad.travelagency.listeners;

import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;
import com.gmail.vleynik.olad.travelagency.utils.TaskExecutorService;
import com.gmail.vleynik.olad.travelagency.utils.tasks.RemoveExpiredEntryTask;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        boolean success = true;
        log.debug("start context initialization");

        try {
            ConnectionUtil.init();
            removeExpiredEntryExecutor.startExecutionAt(LocalTime.of(0, 0), 24);
            log.debug("database connection has been successfully initialized");
        } catch (IOException e) {
            success = false;
            log.fatal("database connection initialization failed");
        }

        try {
            String noImageFile = new File(".").getCanonicalPath() + File.separator
                    + "tours_images" + File.separator + "no-image.jpg";

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("no-image.jpg");

            if (is == null) {
                throw new IOException();
            }

            OutputStream os = new BufferedOutputStream(new FileOutputStream(noImageFile));

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = is.read(buffer)) > 0) {
                os.write(buffer, 0, lengthRead);
                os.flush();
            }

            is.close();
            os.close();
        } catch (IOException e) {
            success = false;
            log.fatal("no-image.jpg coping failed");
        }

        if (success)
            log.debug("application has been successfully initialized");
        else
            log.fatal("application initialization failed");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        removeExpiredEntryExecutor.stop();
        AbandonedConnectionCleanupThread.checkedShutdown();
        log.debug("context destroyed");
    }
}
