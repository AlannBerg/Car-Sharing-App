package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Services.BookingService;
import com.example.CarRentalAplication.Services.ReturnCarService;
import com.example.CarRentalAplication.contract.BookedDTO.BookedDTO;

import com.example.CarRentalAplication.contract.BookedDTO.BookedDTOWithNoID;
import com.example.CarRentalAplication.contract.BookedDTO.ClosedBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingControler {
    private final BookingService bookingService;
    private final ReturnCarService returnCarService;

    @Autowired
    public BookingControler(BookingService bookingService, ReturnCarService returnCarService) {
        this.bookingService = bookingService;
        this.returnCarService = returnCarService;
    }


    @GetMapping("/getbookings")
    public ResponseEntity<List<BookedDTO>> showAllActiveBookingsForClient(@RequestParam Integer clientID){
        return new ResponseEntity<>(bookingService.findActiveBookingsForClientByClientID(clientID),HttpStatus.OK);
    }


    @PostMapping("/bookCar")
    public ResponseEntity<BookedDTOWithNoID> bookACar(@RequestParam Integer clientId,
                                              @RequestParam Integer carId,
                                              @RequestParam String rentalStartingDate,
                                              @RequestParam String rentalEndDate
                                              ){

        BookedDTOWithNoID bookingRequest = new BookedDTOWithNoID(clientId,carId,rentalStartingDate,rentalEndDate);

        return new ResponseEntity<>( bookingService.bookACar(bookingRequest),HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<ClosedBookingDTO> returnACar(@RequestParam Integer bookedID,
                                                       @RequestParam Integer milage,
                                                       @RequestParam Integer returningCityID){

        return new ResponseEntity<>(returnCarService.returnCar(bookedID,milage,returningCityID),HttpStatus.OK);

    }
}

