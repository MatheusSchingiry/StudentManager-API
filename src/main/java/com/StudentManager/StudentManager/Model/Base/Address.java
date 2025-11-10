package com.StudentManager.StudentManager.Model.Base;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;
}
