package be.ordina.spring4.service;

import be.ordina.spring4.model.Person;

import java.util.List;

/**
 * Created by sest on 06/02/15.
 */
public interface PersonService {
    List<Person> findPersons();
}
