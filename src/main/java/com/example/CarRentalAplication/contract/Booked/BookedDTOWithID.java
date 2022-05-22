package com.example.CarRentalAplication.contract.Booked;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
public class BookedDTOWithID extends BookedDTO{

    private Integer bookedID;

    public BookedDTOWithID( Integer bookedID ,Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate) {
        super(clientId, carId, rentalStartingDate, rentalEndDate);
        this.bookedID = bookedID;
    }
}
