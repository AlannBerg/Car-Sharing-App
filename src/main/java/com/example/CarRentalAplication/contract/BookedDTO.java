package com.example.CarRentalAplication.contract;

public class BookedDTO {
    private Integer id;
    private Integer clientId;
    private Integer carId;
    private java.sql.Date rentalDate;
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
