package be.ordina.spring4.asyncclientdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * Created by sest on 21/01/15.
 */
public class AsyncClient {


    public static void main(String[] args) {
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

        Logger.log("Calling service...");

        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.getForEntity("http://localhost:8080/person", String.class);

        future.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

            @Override
            public void onSuccess(ResponseEntity<String> response) {
                Logger.log("Success!!!");
                Logger.log(response.getBody());

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

        Logger.log("Doing other stuff");

    }

}
