package be.ordina.spring4.restapi.service;

import be.ordina.spring4.restapi.model.Person;

import java.util.List;

/**
 * Created by sest on 06/02/15.
 */
public interface PersonService {
    List<Person> findPersons();
    Person findPerson(long id);
}
