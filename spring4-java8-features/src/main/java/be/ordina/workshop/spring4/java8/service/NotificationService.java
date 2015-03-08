package be.ordina.workshop.spring4.java8.service;

import org.springframework.stereotype.Component;

/**
 * Created by stevedezitter on 25/02/15.
 */
@Component
public class NotificationService {

    public void sendNotification(String notification) {
        System.out.println(notification);
    }
}