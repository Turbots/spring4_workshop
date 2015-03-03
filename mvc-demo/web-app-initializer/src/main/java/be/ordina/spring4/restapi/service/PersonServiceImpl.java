package be.ordina.spring4.restapi.service;

import be.ordina.spring4.restapi.model.Address;
import be.ordina.spring4.restapi.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sest on 06/02/15.
 */
@Service
public class PersonServiceImpl implements  PersonService{

    private static final int DELAY = 1000 * 15;

    @Override
    public List<Person> findPersons() {

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return makeListOfPersons();
    }

    @Override
    public Person findPerson(long id) {
        Person foundPerson = new Person();
        for(Person person: makeListOfPersons()){
            if(id==person.getId()){
                foundPerson = person;
            }
        }
        return foundPerson;
    }

    private List<Person> makeListOfPersons(){
        List<Person> persons = new ArrayList<>();
        Address address1 = new Address();
        address1.setStreet("Rabbitholestreet");
        address1.setNumber("25");
        address1.setCity("Wonderful City");
        address1.setPostCode("12345");
        address1.setCountry("Wonderland");
        Person person1 = new Person("Alise", "Liddell", address1);
        person1.setId(1);
        person1.setEmail("alice.liddell@wonderland.wl");
        person1.setPhone("23232323");
        persons.add(person1);

        Address address2 = new Address();
        address1.setStreet("Darkskystreet");
        address1.setNumber("13");
        address1.setCity("Wonderful City");
        address1.setPostCode("12345");
        address1.setCountry("Wonderland");
        Person person2 = new Person("Cat", "Cheshire", address2);
        person2.setId(2);
        person2.setEmail("cat.cheshire@wonderland.wl");
        person2.setPhone("78787878");
        persons.add(person2);

        return persons;
    }
}
