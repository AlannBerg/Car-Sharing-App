package com.example.CarRentalAplication.Exceptions;

public class InvalidLocalizationID extends RuntimeException {
    public InvalidLocalizationID() {
        super("This localization ID does not exist");
    }
}
