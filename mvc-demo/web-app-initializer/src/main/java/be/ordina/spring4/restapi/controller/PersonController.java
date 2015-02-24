package be.ordina.spring4.restapi.controller;

import be.ordina.spring4.restapi.model.Person;
import be.ordina.spring4.restapi.model.View;
import be.ordina.spring4.restapi.service.PersonService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @JsonView(View.Email.class)
    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Person> showPersons() throws InterruptedException {
        List<Person> persons = personService.findPersons();
        return persons;
    }
    @JsonView(View.Phone.class)
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Person showPerson(@PathVariable long id){
        return personService.findPerson(id);

    }

}
