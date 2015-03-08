package be.ordina.workshop.spring4.java8.repository;

import be.ordina.workshop.spring4.java8.config.DataAccessConfiguration;
import be.ordina.workshop.spring4.java8.config.SpringConfiguration;
import be.ordina.workshop.spring4.java8.model.Beer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by stevedezitter on 23/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataAccessConfiguration.class, SpringConfiguration.class})
@WebAppConfiguration
public class BeerRepositoryTest {

    @Autowired
    private BeerRepository beerRepository;

    @Test
    public void getAllBeers() {
        List<Beer> beers = beerRepository.getAllBeers();

        assertNotNull(beers);
        assertEquals(7, beers.size());
    }

    @Test
    public void getBeerById() {
        Beer beer = beerRepository.getBeerById(2l);

        assertNotNull(beer);
        assertEquals("Liefmans goudenband", beer.getName());
    }

    @Test
    public void getBeerByName() {
        Beer beer = beerRepository.getBeerByName("Jupiler");

        assertNotNull(beer);
        assertEquals("Jupiler", beer.getName());
    }

    @Test
    public void getBeerByNameAndAlcoholPercentage() {
        List<Beer> beers = beerRepository.getBeerByNameAndAlcoholPercentage("Liefmans", new BigDecimal(5.0));

        assertNotNull(beers);
        assertEquals(1, beers.size());

        Beer beer = beers.get(0);

        assertEquals("Liefmans goudenband", beer.getName());
    }

    @Test
    public void getBeersLastModifiedTimestampGreaterThan() {
        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 23, 14, 30, 00);

        List<Beer> beers = beerRepository.getBeersLastModifiedTimestampGreaterThan(Timestamp.valueOf(localDateTime));

        beers.stream().forEach(System.out::println);

        assertEquals(2, beers.size());
    }

    @Test
    public void insertBeer() {
        List<Beer> beers = beerRepository.getAllBeers();

        assertEquals(7, beers.size());

        LocalDateTime localDateTime = LocalDateTime.now();

        beerRepository.insertBeer(new Beer("Omer", "Blond lekker bierke!", new BigDecimal(8.0), localDateTime));
        beers = beerRepository.getAllBeers();

        assertEquals(8, beers.size());
    }

    @Test
    public void updateBeer() {
        Beer beer = beerRepository.getBeerById(1l);
        beer.setName("Maes pils");

        beerRepository.updateBeer(beer);

        Beer maesPils = beerRepository.getBeerById(1l);

        assertEquals("Maes pils", maesPils.getName());
    }
}