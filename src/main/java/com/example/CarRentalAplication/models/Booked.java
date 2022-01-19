package com.example.CarRentalAplication.models;

import javax.persistence.*;

@Entity
@Table(name = "booked")
public class Booked {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "rental_date")
    private java.sql.Date rentalDate;

    @Column(name = "rental_days")
    private Integer rentalDays;

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

    public java.sql.Date getRentalDate() {
        return this.rentalDate;
    }

    public void setRentalDate(java.sql.Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Integer getRentalDays() {
        return this.rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }
}
