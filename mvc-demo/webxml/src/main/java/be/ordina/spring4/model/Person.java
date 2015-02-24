package be.ordina.spring4.model;

import lombok.Data;

/**
 * Created by sest on 06/02/15.
 */
@Data
public class Person {

    private String firstName;
    private String lastName;
    private Address address;

    public Person(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
