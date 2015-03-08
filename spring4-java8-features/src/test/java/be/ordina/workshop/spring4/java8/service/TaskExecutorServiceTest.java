package be.ordina.workshop.spring4.java8.service;

import be.ordina.workshop.spring4.java8.config.DataAccessConfiguration;
import be.ordina.workshop.spring4.java8.config.SpringConfiguration;
import be.ordina.workshop.spring4.java8.model.Beer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by stevedezitter on 23/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataAccessConfiguration.class, SpringConfiguration.class})
@WebAppConfiguration
public class TaskExecutorServiceTest {

    @Autowired
    private TaskExecutorService taskExecutorService;

    @Test
    public void executeTask() {
        System.out.println("Current service id: " + Thread.currentThread());

        taskExecutorService.executeTask(() -> {
            System.out.println("Background service id: " + Thread.currentThread());
        });
    }

    @Test
    public void getBeerAsynchronously() throws InterruptedException {
        System.out.println("getAllBeers: " + LocalDateTime.now());

        ListenableFuture<List<Beer>> future = taskExecutorService.getBeersAsyncUsingTaskExecutor();

        future.addCallback(new SuccessCallback<List<Beer>>() {
            @Override
            public void onSuccess(List<Beer> beers) {
                //Handle success
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                //Handle Failure
            }
        });

        future.addCallback((beers) -> {
                System.out.println("All beers retrieved: " + LocalDateTime.now());
                beers.stream().map(Beer::toString).forEach(System.out::println);
            },
            (throwable) -> {
                System.out.println("An error occured during beer retrieval!: " + LocalDateTime.now());
            });

        //Wait for the response to return........Shouldn't be done in unit tests!
        Thread.sleep(3000);
    }

    @Test
    public void getBeersAsynchronouslyUsingAnnotations() throws InterruptedException, ExecutionException{

        Future<List<Beer>> future = taskExecutorService.getBeersAsyncUsingAnnotationsAndFuture();

        List<Beer> beers = future.get();

        beers.stream().forEach(System.out::println);

        //Wait for the response to return........Shouldn't be done in unit tests!
        Thread.sleep(3000);
    }

    @Test
    public void getBeersAsynchronouslyUsingAnnotationsAndListenableFutures() throws InterruptedException, ExecutionException{
        ListenableFuture<List<Beer>> listenableFuture = taskExecutorService.getBeersAsyncUsingAnnotationsAndListenableFuture();

        listenableFuture.addCallback((beers) -> {
                System.out.println("All beers retrieved: " + LocalDateTime.now());
                beers.stream().map(Beer::toString).forEach(System.out::println);
            },
            (throwable) -> {
                System.out.println("An error occured during beer retrieval!: " + LocalDateTime.now());
                //Handle failure
            }
        );

        //Wait for the response to return........Shouldn't be done in unit tests!
        Thread.sleep(3000);
    }

    @Test
    public void runThreadNotInterruptedByUnitTestThread() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("bla!");
            } catch (InterruptedException e) {
                System.out.println("Thread being interrupted by main unit test service!");
            }

        });

        thread.run();

        System.out.println("End of unit test!");
    }
}
