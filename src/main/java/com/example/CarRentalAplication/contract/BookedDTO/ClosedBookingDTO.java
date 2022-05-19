package com.example.CarRentalAplication.contract.BookedDTO;

import lombok.Getter;
import lombok.Setter;


// a booking where the car is returned and the payment has been submitted
@Setter
@Getter
public class ClosedBookingDTO {

    private Integer bookedID;
    private Integer clientId;
    private Integer carId;
    private String rentalStartingDate;
    private String  rentalEndDate;
    private Integer milage;
    private Float charge;

    public ClosedBookingDTO(Integer bookedID,Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate, Integer milage, Float charge) {
        this.bookedID = bookedID;
        this.clientId = clientId;
        this.carId = carId;
        this.rentalStartingDate = rentalStartingDate;
        this.rentalEndDate = rentalEndDate;
        this.milage = milage;
        this.charge = charge;

    }
}
