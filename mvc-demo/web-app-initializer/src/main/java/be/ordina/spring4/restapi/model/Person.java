package be.ordina.spring4.restapi.model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by sest on 06/02/15.
 */

public class Person {


    private long id;

    @JsonView(View.Email.class)
    private String firstName;

    @JsonView(View.Email.class)
    private String lastName;

    @JsonView(View.Email.class)
    private String email;

    @JsonView(View.Phone.class)
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Address address;

    public Person(){

    }

    public Person(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
