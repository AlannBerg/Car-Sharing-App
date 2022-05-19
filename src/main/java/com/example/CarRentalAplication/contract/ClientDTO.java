package com.example.CarRentalAplication.contract;

import com.example.CarRentalAplication.models.Client;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Setter
@Getter
public class ClientDTO {

    private String name;
    private String lastName;
    private String birthday;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private Byte active;


    public ClientDTO(String name, String lastName, String birthday, String email, String phoneNumber, String  password){
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Client dtoTOentity(){
        Client client = new Client();

        client.setName(this.name);
        client.setLastName(this.lastName);
        client.setBirthday(stringToDate(this.birthday));
        client.setEmail(this.email);
        client.setPhoneNumber(this.phoneNumber);
        client.setPassword(this.password);
        client.setRole("USER");
        client.setActive((byte) 1);

        return client;
    }

    private Date stringToDate(String birthday) {
        return Date.valueOf(birthday);
    }
}

