package com.example.CarRentalAplication.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LocalizationDTO {

    private String city;
    private String postalCode;
    private String street;
    private String localNumber;

}
