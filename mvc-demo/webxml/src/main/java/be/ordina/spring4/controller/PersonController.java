package be.ordina.spring4.controller;

import be.ordina.spring4.model.Person;
import be.ordina.spring4.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Person> showMessage() throws InterruptedException {
        List<Person> persons = personService.findPersons();
        System.out.println(persons);
        return persons;
    }

}
