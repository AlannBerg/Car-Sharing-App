package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Services.BookingService;
import com.example.CarRentalAplication.contract.BookedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping("/booking")
public class BookingControler {
    private BookingService bookingService;

    @Autowired
    public BookingControler(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookCar")
    public ResponseEntity<BookedDTO> bookACar(@RequestParam Integer clientId,
                                              @RequestParam Integer carId,
                                              @RequestParam String rentalStartingDate,
                                              @RequestParam String rentalEndDate
                                              ){

        BookedDTO bookingRequest = new BookedDTO(clientId,carId,rentalStartingDate,rentalEndDate);


        return new ResponseEntity<>( bookingService.bookACar(bookingRequest),HttpStatus.OK);
    }

}

