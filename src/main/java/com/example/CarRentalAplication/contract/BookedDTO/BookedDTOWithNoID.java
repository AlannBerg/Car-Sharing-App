package com.example.CarRentalAplication.contract.BookedDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookedDTOWithNoID {

    private Integer clientId;
    private Integer carId;
    private String rentalStartingDate;
    private String  rentalEndDate;

    public BookedDTOWithNoID(Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate) {
        this.clientId = clientId;
        this.carId = carId;
        this.rentalStartingDate = rentalStartingDate;
        this.rentalEndDate = rentalEndDate;
    }
}
