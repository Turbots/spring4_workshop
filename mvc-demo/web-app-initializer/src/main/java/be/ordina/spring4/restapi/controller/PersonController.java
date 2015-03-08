package be.ordina.spring4.restapi.controller;

import be.ordina.spring4.restapi.model.Person;
import be.ordina.spring4.restapi.model.View;
import be.ordina.spring4.restapi.service.PersonService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.TimeZone;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @JsonView(View.Email.class)
    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
    public List<Person> showPersons(HttpServletRequest request) throws InterruptedException {
        List<Person> persons = personService.findPersons();
        return persons;
    }

    @JsonView(View.Phone.class)
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
    public Person showPerson(@PathVariable long id){
        return personService.findPerson(id);

    }

}
