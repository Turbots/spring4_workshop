package be.ordina.workshop.spring4.java8.controller;

import be.ordina.workshop.spring4.java8.model.Beer;
import be.ordina.workshop.spring4.java8.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by stevedezitter on 24/02/15.
 */
@RestController
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerRepository beerRepository;

    @RequestMapping(value = "/{modifiedDate}", method = RequestMethod.GET)
    public List<Beer> getBeersModifiedAfterDate(@DateTimeFormat(pattern = "dd-MM/-yyyy hh:mm:ss") LocalDateTime modifiedDate) {
        List<Beer> beers = beerRepository.getBeersLastModifiedTimestampGreaterThan(Timestamp.valueOf(modifiedDate));

        return beers;
    }

}
