package com.example.CarRentalAplication.contract.Booked;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookedDTO {

    private Integer clientId;
    private Integer carId;
    private String rentalStartingDate;
    private String  rentalEndDate;

    public BookedDTO(Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate) {
        this.clientId = clientId;
        this.carId = carId;
        this.rentalStartingDate = rentalStartingDate;
        this.rentalEndDate = rentalEndDate;
    }
}
