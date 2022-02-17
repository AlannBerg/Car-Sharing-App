package com.example.CarRentalAplication.Exceptions;

public class InvalidClientID extends RuntimeException {
    public InvalidClientID() {
        super("This client ID does not exist");
    }
}
