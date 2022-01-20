package com.example.CarRentalAplication.contract;

import com.example.CarRentalAplication.models.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
public class ClientDTO {

    private String name;
    private String lastName;
    private String birthday;
    private String email;
    private String phoneNumber;


    public Client dtoTOentity(){
        Client client = new Client();
        client.setName(this.name);
        client.setLastName(this.lastName);
        client.setBirthday(stringToDate(this.birthday));
        client.setEmail(this.email);
        client.setPhoneNumber(this.phoneNumber);
        return client;
    }

    private Date stringToDate(String birthday) {
        return Date.valueOf(birthday);
    }
}

