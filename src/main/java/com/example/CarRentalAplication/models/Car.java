package com.example.CarRentalAplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private Byte available;

    @Column(name = "current_location")
    private Integer currentLocation;

}
