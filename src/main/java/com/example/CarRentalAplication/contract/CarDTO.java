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
    private Byte available;
    private String currentLocation;


    public CarDTO(String make, String model, String color, Integer year, Integer horsepower, Integer rentfee, Byte available, String currentLocation) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.horsepower = horsepower;
        this.rentfee = rentfee;
        this.available = available;
        this.currentLocation = currentLocation;
    }



    public Car dtoToEntity(){
        Car car = new Car();
        car.setMake(this.make);
        car.setModel(this.model);
        car.setColor(this.color);
        car.setYear(this.year);
        car.setHorsepower(this.horsepower);
        car.setRentfee(this.rentfee);
        car.setAvailable(this.available);
        car.setCurrentLocation(Integer.valueOf(this.currentLocation));
        return car;
    }
}
