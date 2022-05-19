package com.example.CarRentalAplication.contract.BookedDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
public class BookedDTO {

    private Integer bookedID;
    private Integer clientId;
    private Integer carId;
    private String rentalStartingDate;
    private String  rentalEndDate;


    public BookedDTO(Integer bookedID, Integer clientId, Integer carId, String rentalStartingDate, String rentalEndDate) {
        this.bookedID = bookedID;
        this.clientId = clientId;
        this.carId = carId;
        this.rentalStartingDate = rentalStartingDate;
        this.rentalEndDate = rentalEndDate;
    }



}
