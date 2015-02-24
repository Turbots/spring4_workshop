package be.ordina.workshop.spring4.java8.repository;

import be.ordina.workshop.spring4.java8.model.Beer;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by stevedezitter on 19/02/15.
 */
public interface BeerRepository {

    List<Beer> getAllBeers();

    Beer getBeerByName(String name);

    Beer getBeerById(Long id);

    List<Beer> getBeerByNameAndAlcoholPercentage(String name, BigDecimal alcoholPercentage);

    void insertBeer(Beer beer);

    void updateBeer(Beer beer);

}
