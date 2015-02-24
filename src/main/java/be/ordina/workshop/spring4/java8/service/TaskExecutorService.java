package be.ordina.workshop.spring4.java8.service;

import be.ordina.workshop.spring4.java8.model.Beer;
import be.ordina.workshop.spring4.java8.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by stevedezitter on 24/02/15.
 */
@Service
public class TaskExecutorService {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private BeerRepository beerRepository;

    public void executeTask(Runnable task) {
        threadPoolTaskExecutor.execute(task);
    }

    public ListenableFuture<List<Beer>> getBeersAsyncUsingTaskExecutor() {
        return threadPoolTaskExecutor.submitListenable(() -> {
            try {
                Thread.sleep(2000);
                List<Beer> beers = beerRepository.getAllBeers();
                return beers;
            }catch(Throwable throwable){
                System.out.println(throwable);
                throw new RuntimeException("blablabla");
            }
        });
    }

    @Async("myExecutor")
    public Future<List<Beer>> getBeersAsyncUsingAnnotationsAndFuture() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Beer> beers = beerRepository.getAllBeers();

        return new AsyncResult<>(beers);
    }

    @Async("myExecutor")
    public ListenableFuture<List<Beer>> getBeersAsyncUsingAnnotationsAndListenableFuture() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Beer> beers = beerRepository.getAllBeers();

        return new AsyncResult<>(beers);
    }
}
