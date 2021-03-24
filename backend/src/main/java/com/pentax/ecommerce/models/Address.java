package com.pentax.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    private String id;
    private String houseNumber;
    private String Street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
