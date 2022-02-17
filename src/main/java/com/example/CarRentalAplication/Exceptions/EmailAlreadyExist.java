package com.example.CarRentalAplication.Exceptions;

public class EmailAlreadyExist extends RuntimeException {
    public EmailAlreadyExist() {
        super("This email is already in use");
    }
}
