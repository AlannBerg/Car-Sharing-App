package com.example.CarRentalAplication.Exceptions;

public class RequestedTermIsNotCorrect extends RuntimeException {
    public RequestedTermIsNotCorrect() {
        super("This term is not correct, please try again ");
    }
}
