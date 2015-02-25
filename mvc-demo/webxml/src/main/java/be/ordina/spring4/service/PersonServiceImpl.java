package be.ordina.spring4.service;

import be.ordina.spring4.model.Address;
import be.ordina.spring4.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sest on 06/02/15.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public List<Person> findPersons() {
        return makeListOfPersons();
    }

    private List<Person> makeListOfPersons() {
        List<Person> persons = new ArrayList<>();
        Address address1 = new Address();
        address1.setStreet("Rabbitholestreet");
        address1.setNumber("25");
        address1.setCity("Wonderful City");
        address1.setPostCode("12345");
        address1.setCountry("Wonderland");
        Person person1 = new Person("Alise", "Liddell", address1);
        persons.add(person1);

        return persons;
    }
}
