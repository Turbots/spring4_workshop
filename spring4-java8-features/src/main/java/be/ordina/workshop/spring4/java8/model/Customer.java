package be.ordina.workshop.spring4.java8.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by stevedezitter on 24/02/15.
 */
public class Customer {

    @DateTimeFormat(pattern = "ddMMyyyy")
    private LocalDate birthDate;

    @DateTimeFormat(pattern = "ddMMyyyy hh:mm:ss")
    private LocalDateTime lastContact;

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getLastContact() {
        return lastContact;
    }
    public void setLastContact(LocalDateTime lastContact) {
        this.lastContact = lastContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!birthDate.equals(customer.birthDate)) return false;
        if (!lastContact.equals(customer.lastContact)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = birthDate.hashCode();
        result = 31 * result + lastContact.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "birthDate=" + birthDate +
                ", lastContact=" + lastContact +
                '}';
    }
}