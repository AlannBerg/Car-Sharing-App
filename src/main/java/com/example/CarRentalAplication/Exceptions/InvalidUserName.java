package com.example.CarRentalAplication.Exceptions;

public class InvalidUserName extends RuntimeException {
    public InvalidUserName() {
        super("This user name is invalid");
    }
}
