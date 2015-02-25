package be.ordina.spring4.asyncclientdemo;

import java.util.Date;

/**
 * Created by sest on 21/01/15.
 */
public class Logger {

    public static void log(String message) {
        System.out.println(String.format("%s %s %s", new Date(), Thread.currentThread().getName(), message));
    }
}
