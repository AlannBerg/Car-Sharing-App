package com.example.CarRentalAplication.Exceptions;

public class InvalidCarID extends RuntimeException {
    public InvalidCarID() {
        super("This car id does not exist");
    }
}
