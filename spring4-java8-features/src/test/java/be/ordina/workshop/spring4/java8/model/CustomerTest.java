package be.ordina.workshop.spring4.java8.model;

import be.ordina.workshop.spring4.java8.config.DataAccessConfiguration;
import be.ordina.workshop.spring4.java8.config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by stevedezitter on 24/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class, DataAccessConfiguration.class})
@WebAppConfiguration
public class CustomerTest {

    @Autowired
    private Customer customer;

    @DateTimeFormat(pattern = "ddMMyyyy")
    private LocalDate localDateTime;

    @Test
    public void customerToStringWithDateTimeFormat() {
        customer.setBirthDate(LocalDate.of(1985, Month.SEPTEMBER, 22));
        customer.setLastContact(LocalDateTime.of(2015, Month.JANUARY, 28, 14, 30, 23));

        localDateTime = LocalDate.now();

        System.out.println(localDateTime);
    }
}
