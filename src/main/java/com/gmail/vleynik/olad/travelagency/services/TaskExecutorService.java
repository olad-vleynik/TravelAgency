package com.gmail.vleynik.olad.travelagency.services;

import org.apache.log4j.Logger;

import java.time.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskExecutorService {

    private static final Logger log = Logger.getLogger(TaskExecutorService.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final Runnable task;

    public TaskExecutorService(Runnable task)
    {
        this.task = task;

    }

    public void startExecutionAt(LocalTime targetTime, long delayInHours)
    {
        long initDelay = Duration.between(LocalTime.now(), targetTime).getSeconds() / 60 / 60;

        executorService.scheduleWithFixedDelay(task, initDelay, delayInHours, TimeUnit.HOURS);
    }

    public void stop()
    {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            log.error(ex);
            Thread.currentThread().interrupt();
        }
    }
}
