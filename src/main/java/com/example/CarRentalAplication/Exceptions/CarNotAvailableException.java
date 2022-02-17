package com.example.CarRentalAplication.Exceptions;


public class CarNotAvailableException extends RuntimeException{

    public CarNotAvailableException() {
        super("Car is not available at this moment ");
    }
}
