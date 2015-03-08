package be.ordina.workshop.spring4.java8.controller;

import be.ordina.workshop.spring4.java8.config.DataAccessConfiguration;
import be.ordina.workshop.spring4.java8.config.SpringConfiguration;
import be.ordina.workshop.spring4.java8.model.Beer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stevedezitter on 25/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class, DataAccessConfiguration.class})
@WebAppConfiguration
public class BeerControllerIntegrationTest {

    @Test
    public void getBeersModifiedAfterDateWithCountryHeader() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Arrays.asList(new MyRequestInterceptor()));

        ResponseEntity<List<Beer>> beersResponseEntity = template.exchange("http://localhost:8080/spring4workshop/beers/modified/{1}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Beer>>() {}, "220220151430");

        List<Beer> beers = beersResponseEntity.getBody();

        beers.stream().forEach(System.out::println);
    }

    @Test
    public void getBeersModifiedAfterDateWithoutCountryHeader() {
        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Beer>> beersResponseEntity = template.exchange("http://localhost:8080/spring4workshop/beers/modified/{1}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Beer>>() {}, "220220151430");

        List<Beer> beers = beersResponseEntity.getBody();

        beers.stream().forEach(System.out::println);

        //When @RequestHeader in the controller is not marked required=false, this will throw an HttpClientErrorException -> 400 Bad request
        //Assert that the exception is being thrown!?
    }

    @Test
    public void getBeerByIdWithCountryHeader() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Arrays.asList(new MyRequestInterceptor()));

        Beer beer = template.getForObject("http://localhost:8080/spring4workshop/beers/{1}", Beer.class, 1l);

        System.out.println(beer);
    }

    @Test
    public void getBeerByIdWithoutCountryHeaderAndWithoutId() {
        RestTemplate template = new RestTemplate();
//        template.setInterceptors(Arrays.asList(new MyRequestInterceptor()));

        Beer beer = template.getForObject("http://localhost:8080/spring4workshop/beers/", Beer.class);

        System.out.println(beer);
    }

    @Test
    public void getBeerByIdWithoutCountryHeader() {
        RestTemplate template = new RestTemplate();

        Beer beer = template.getForObject("http://localhost:8080/spring4workshop/beers/{1}", Beer.class, 1l);

        System.out.println(beer);

        //When @RequestHeader in the controller is not marked required=false, this will throw an HttpClientErrorException -> 400 Bad request
        //Assert that the exception is being thrown!?
    }

    private class MyRequestInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            httpRequest.getHeaders().set("country", "blabla");

            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }
    }
}
