package com.example.CarRentalAplication.Exceptions;

public class InvalidBookingID extends RuntimeException {
    public InvalidBookingID() {
        super("This booking does not exist");
    }
}
