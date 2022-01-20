package com.example.CarRentalAplication.models;

import javax.persistence.*;

@Entity
@Table(name = "booked")
public class Booked {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "rental_starting_date")
    private java.sql.Date rentalStartingDate;

    @Column(name = "rental_end_date")
    private java.sql.Date rentalEndDate;

    @Column(name = "milage")
    private Integer milage;

    @Column(name = "charge")
    private Float charge;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCarId() {
        return this.carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public java.sql.Date getRentalStartingDate() {
        return this.rentalStartingDate;
    }

    public void setRentalStartingDate(java.sql.Date rentalStartingDate) {
        this.rentalStartingDate = rentalStartingDate;
    }

    public java.sql.Date getRentalEndDate() {
        return this.rentalEndDate;
    }

    public void setRentalEndDate(java.sql.Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Integer getMilage() {
        return this.milage;
    }

    public void setMilage(Integer milage) {
        this.milage = milage;
    }

    public Float getCharge() {
        return this.charge;
    }

    public void setCharge(Float charge) {
        this.charge = charge;
    }
}
