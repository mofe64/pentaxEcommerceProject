package com.pentax.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
