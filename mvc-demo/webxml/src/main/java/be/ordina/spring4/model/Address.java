package be.ordina.spring4.model;

import lombok.Data;

/**
 * Created by sest on 06/02/15.
 */
@Data
public class Address {
    private String street;
    private String number;
    private String postCode;
    private String city;
    private String country;
}
