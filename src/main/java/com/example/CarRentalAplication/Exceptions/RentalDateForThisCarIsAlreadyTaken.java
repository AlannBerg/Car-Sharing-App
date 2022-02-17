package com.example.CarRentalAplication.Exceptions;

public class RentalDateForThisCarIsAlreadyTaken extends RuntimeException {

    public RentalDateForThisCarIsAlreadyTaken() {
        super("This rental term is already taken ");
    }
}
