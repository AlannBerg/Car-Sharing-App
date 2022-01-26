package com.example.CarRentalAplication.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UnregisteredClientDTO{
    private String name;
    private String lastName;
    private String birthday;
    private String email;
    private String phoneNumber;
    private String password;
}
