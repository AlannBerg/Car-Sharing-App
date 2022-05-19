package com.example.CarRentalAplication.Services;


import com.example.CarRentalAplication.contract.BookedDTO.ClosedBookingDTO;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapperImpl;
import com.example.CarRentalAplication.models.Booked;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ReturnCarService {

    private BookingService bookingService;
    private CarService carService;
    private LocalizationService localizationService;
    private CarSharingAppMapperImpl carSharingAppMapper = new CarSharingAppMapperImpl();

    @Autowired
    public ReturnCarService(BookingService bookingService, CarService carService, LocalizationService localizationService) {
        this.bookingService = bookingService;
        this.carService = carService;
        this.localizationService = localizationService;
    }

    @SneakyThrows
    public ClosedBookingDTO returnCar(Integer bookedID, Integer milage, Integer returningCityID) {

        Booked booked = bookingService.findActiveBookingByID(bookedID);

        int rentalLenght = getRentalLenght(booked);
        Float charge = evaluateCharge(booked ,milage, rentalLenght);

        // changing the car localization if client return it in other company branch
        // if milage = 0 that means client resigned from renting and car is in the same place
        if(milage != 0){
            bookingService.changeCarLocalization(booked,returningCityID);
        }

        booked.setMilage(milage);
        booked.setCharge(charge);

        bookingService.closeRentalandSave(booked);

        return carSharingAppMapper.bookedToClosedBookingDTO(booked);

    }

    private Float evaluateCharge(Booked booked, Integer milage, Integer rentalLenght) {
        // if client resigns from renting a car and millage = 0 the cost is also 0;
        if(milage != 0){
            // in this company daily km limit is 300km.
            // if client exceeded the limit, charge will be enlarged by extra costs
            Float extraCost =0f;
            int milageLimit = rentalLenght * 300;
            if(milageLimit < milage){
                extraCost = (milage - milageLimit) * 3.5f;
            }

            Integer rentFee = carService.findRentalFeeByID(booked.getCarId());

            return (rentFee * rentalLenght + extraCost);
        }else {
            return 0f;
        }
    }

    private int getRentalLenght(Booked booked) {
        LocalDate rentalStarting = LocalDate.parse(booked.getRentalStartingDate().toString());
        LocalDate rentalEnding = LocalDate.parse(booked.getRentalEndDate().toString());

        return (int) ChronoUnit.DAYS.between(rentalStarting,rentalEnding);
    }
}
