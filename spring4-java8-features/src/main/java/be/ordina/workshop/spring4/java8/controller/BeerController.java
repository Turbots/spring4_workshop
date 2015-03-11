package be.ordina.workshop.spring4.java8.controller;

import be.ordina.workshop.spring4.java8.model.Beer;
import be.ordina.workshop.spring4.java8.repository.BeerRepository;
import be.ordina.workshop.spring4.java8.service.MyService;
import be.ordina.workshop.spring4.java8.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by stevedezitter on 24/02/15.
 */
@RestController
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerRepository beerRepository;

//    @Autowired
//    private List<MyService> services;

    @Autowired
    @Qualifier("myServiceImpl")
    private MyService service; //Injects MyServiceImpl

//    @Autowired
//    private Optional<NotificationService> notificationService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/modified/{modifiedDate}", method = RequestMethod.GET)
    public List<Beer> getBeersModifiedAfterDate(
            @PathVariable @DateTimeFormat(pattern="ddMMyyyyHHmm") LocalDateTime modifiedDate,
            @RequestHeader(required = false) String country) {
        notificationService.sendNotification("getBeersModifiedAfterDate date: " + modifiedDate + "\n country header: " + country);

        return beerRepository.getBeersLastModifiedTimestampGreaterThan(Timestamp.valueOf(modifiedDate));
    }

    /*
    getBeerById method using parameter name discovery and Java 8 Optional
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Beer getBeerById(@PathVariable Long id, @RequestHeader Optional<String> country) {
//        country.ifPresent(System.out::println);
//
//        return beerRepository.getBeerById(id);
//    }

    /*
    getBeerId method using parameter name discovery with required=false attribute on @RequestHeader
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Beer getBeerById(@PathVariable Long id, @RequestHeader(required=false) String country) {
//        if(country!=null)
//            System.out.println(country);
//
//        return beerRepository.getBeerById(id);
//    }

    /*
    getBeerId method without parameter name discovery and @RequestHeader(required = false) attribute
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Beer getBeerById(@PathVariable("id") Long id, @RequestHeader(value = "country", required = false) String country) {
//        if(country != null) {
//            System.out.println(country);
//        }
//        return beerRepository.getBeerById(id);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable Long id, @RequestHeader Optional<String> country) {
        notificationService.sendNotification("getBeerForId: " + id);

        country.ifPresent(header -> notificationService.sendNotification(header));

        return beerRepository.getBeerById(id);
    }

    @RequestMapping(value="/injectedList")
    public void injectedLists() {
//        services.stream().forEach(service -> service.someAction());

        service.someAction();
    }
}
