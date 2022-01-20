package com.example.CarRentalAplication.contract;

import com.example.CarRentalAplication.models.Car;
import lombok.*;

@Setter
@Getter
public class CarDTO {
    private String make;
    private String model;
    private String color;
    private Integer year;
    private Integer horsepower;
    private Integer rentfee;
    private Boolean available;
    private String currentLocation;


    public CarDTO(String make, String model, String color, Integer year, Integer horsepower, Integer rentfee, Byte available, String currentLocation) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.horsepower = horsepower;
        this.rentfee = rentfee;
        this.available = isAailable(available);
        this.currentLocation = currentLocation;
    }


    private Boolean isAailable(Byte available) {
        return available == 1;
    }
}
