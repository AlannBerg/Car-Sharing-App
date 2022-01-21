package com.example.CarRentalAplication.contract;

import com.example.CarRentalAplication.models.Booked;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class BookedDTO {

    private Integer clientId;
    private Integer carId;
    private String rentalStartingDate;
    private String  rentalEndDate;
    private Integer milage;
    private Float charge;


    public BookedDTO(Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate) {
        this.clientId = clientId;
        this.carId = carId;
        this.rentalStartingDate = rentalStartingDate;
        this.rentalEndDate = rentalEndDate;
    }

    public BookedDTO(Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate, Integer milage, Float charge) {
        this.clientId = clientId;
        this.carId = carId;
        this.rentalStartingDate = rentalStartingDate;
        this.rentalEndDate = rentalEndDate;
        this.milage = milage;
        this.charge = charge;

    }
    public Booked dtoToEntity(){
        Booked booked = new Booked();
        booked.setClientId(this.clientId);
        booked.setCarId(this.carId);
        booked.setRentalStartingDate(Date.valueOf(this.getRentalStartingDate()));
        booked.setRentalEndDate(Date.valueOf(this.getRentalEndDate()));
        return booked;
    }
}
