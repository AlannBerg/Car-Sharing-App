package com.example.CarRentalAplication.contract.Booked;

import lombok.Getter;
import lombok.Setter;


// a booking where the car is returned and the payment has been submitted
@Setter
@Getter
public class ClosedBookingDTO extends BookedDTOWithID {

    private Integer milage;
    private Float charge;

    public ClosedBookingDTO(Integer bookedID, Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate, Integer milage, Float charge) {
        super(bookedID, clientId, carId, rentalStartingDate, rentalEndDate);
        this.milage = milage;
        this.charge = charge;
    }
}
