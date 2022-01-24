package com.example.CarRentalAplication.models;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "year")
    private Integer year;

    @Column(name = "horsepower")
    private Integer horsepower;

    @Column(name = "rentfee")
    private Integer rentfee;

    @Column(name = "available")
    private Integer available;

    @Column(name = "current_location")
    private Integer currentLocation;

    public Car() {
    }

    public Car(Integer id, String make, String model, String color, Integer year, Integer horsepower, Integer rentfee, Integer available, Integer currentLocation) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.horsepower = horsepower;
        this.rentfee = rentfee;
        this.available = available;
        this.currentLocation = currentLocation;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHorsepower() {
        return this.horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getRentfee() {
        return this.rentfee;
    }

    public void setRentfee(Integer rentfee) {
        this.rentfee = rentfee;
    }

    public Integer getAvailable() {
        return this.available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(Integer currentLocation) {
        this.currentLocation = currentLocation;
    }
}
