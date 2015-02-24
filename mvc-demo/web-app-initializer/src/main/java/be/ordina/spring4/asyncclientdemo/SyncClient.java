package be.ordina.spring4.asyncclientdemo;

import org.springframework.web.client.RestTemplate;

/**
 * Created by sest on 21/01/15.
 */
public class SyncClient {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        Logger.log("Calling service...");

        String result = restTemplate.getForObject("http://localhost:8080/person", String.class);

        Logger.log(result);

        Logger.log("Doing other stuff");


    }
}
