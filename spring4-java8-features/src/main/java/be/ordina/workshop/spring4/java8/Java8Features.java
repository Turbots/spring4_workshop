package be.ordina.workshop.spring4.java8;

import be.ordina.workshop.spring4.java8.config.DataAccessConfiguration;
import be.ordina.workshop.spring4.java8.config.SpringConfiguration;
import be.ordina.workshop.spring4.java8.model.Beer;
import be.ordina.workshop.spring4.java8.repository.BeerRepository;
import be.ordina.workshop.spring4.java8.service.TaskExecutorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.concurrent.ListenableFuture;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by stevedezitter on 19/02/15.
 */
public class Java8Features {

    private ApplicationContext applicationContext;
    private BeerRepository beerRepository;
    private TaskExecutorService taskExecutor;

    public static void main(String[] args){
        Java8Features app = new Java8Features();

        app.startApplication();
    }

    private void startApplication() {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class, DataAccessConfiguration.class);

        beerRepository = applicationContext.getBean(BeerRepository.class);
        taskExecutor = applicationContext.getBean(TaskExecutorService.class);

        displayAllBeers();

        displayBeerByName("Jupiler");

        displayBeerById(2l);

        displayBeersByNameAndAlcoholPercentage("Liefmans", new BigDecimal(5.0));

//        displayBeersAsyncUsingTaskExecutor();
        displayBeersAsyncUsingAnnotationsAndFuture();
    }

    private void displayAllBeers() {
        System.out.println("GET_ALL_BEERS");
        List<Beer> beers = beerRepository.getAllBeers();

        beers.stream().forEach(beer -> System.out.println(beer.toString()));
    }

    private void displayBeerByName(String name) {
        System.out.println("GET_BEER_BY_NAME");
        Beer beer = beerRepository.getBeerByName(name);

        System.out.println(beer);
    }

    private void displayBeerById(long id) {
        System.out.println("GET_BEER_BY_ID");
        Beer beer = beerRepository.getBeerById(id);

        System.out.println(beer);
    }

    private void displayBeersByNameAndAlcoholPercentage(String name, BigDecimal alcoholPercentage) {
        System.out.println("SELECT_BEER_BY_NAME_AND_ALCOHOL_PERCENTAGE");

        List<Beer> beers = beerRepository.getBeerByNameAndAlcoholPercentage(name, alcoholPercentage);
        beers.stream().map(beer -> beer.toString()).forEach(System.out::println);
    }

    private void displayBeersAsyncUsingTaskExecutor() {
        System.out.println("displayBeersAsynchronously: " + LocalDateTime.now());

        ListenableFuture<List<Beer>> future = taskExecutor.getBeersAsyncUsingTaskExecutor();

        future.addCallback((beers) -> {
                System.out.println("displayBeersAsynchronously: " + LocalDateTime.now());
                beers.stream().forEach(System.out::println);
            },

            (throwable) -> {
                System.out.println("An error occured during displayBeersAsynchronously: " + LocalDateTime.now());
            });
    }

    private void displayBeersAsyncUsingAnnotationsAndFuture() {
        System.out.println("displayBeersAsynchronously2: " + LocalDateTime.now());

        Future<List<Beer>> future = taskExecutor.getBeersAsyncUsingAnnotationsAndFuture();

        try {
            List<Beer> beers = future.get();

            beers.stream().forEach(System.out::println);
        } catch (InterruptedException |
                ExecutionException e) {
            e.printStackTrace();
        }
    }
}
