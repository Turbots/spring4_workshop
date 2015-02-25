package be.ordina.workshop.spring4.java8.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by stevedezitter on 25/02/15.
 */
@Component
public class ScheduledTasks {

    @Value("${message}")
    private String message;

    @Value("${anotherMessage}")
    private String anotherMessage;

    public String getMessage() {
        return message;
    }

    public String getAnotherMessage() {
        return anotherMessage;
    }

    @Schedules({
            @Scheduled(fixedDelay = 2000),
            @Scheduled(fixedDelay = 10000)
    })
    public void scheduledTask() {
        System.out.println(message + LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 30000)
    public void anotherScheduledTask() {
        System.out.println(anotherMessage + LocalDateTime.now());
    }
}
