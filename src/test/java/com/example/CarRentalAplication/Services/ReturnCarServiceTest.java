package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.models.Booked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReturnCarServiceTest {
    @Mock
    private BookingService bookingService;
    @Mock
    private CarService carService;
    @Mock
    private LocalizationService localizationService;

    private Booked booked = new Booked(
            1,
            1,
            1,
            Date.valueOf("2000-01-01"),
            Date.valueOf("2000-01-02")
    );

    private ReturnCarService returnCarService;

    @BeforeEach
    void setUp() {
        returnCarService = new ReturnCarService(bookingService,carService,localizationService);
    }

    @Test
    void returnCarchargeshouldBe1000AndMilage250(){
        Integer bookedID = 1;
        Integer milage = 250;
        Integer returningCityID = 3;
        Integer rentFee = 1000;

        when(bookingService.findActiveBookingByID(bookedID)).thenReturn(booked);
        when(carService.findRentalFeeByID(booked.getCarId())).thenReturn(rentFee);

        returnCarService.returnCar(bookedID, milage, returningCityID);

        assertEquals(1000,booked.getCharge());
        assertEquals(250, booked.getMilage());
        verify(bookingService,times(1)).changeCarLocalization(booked,returningCityID);
        verify(bookingService, times(1)).closeRentalandSave(booked);
    }
    @Test
    void returnCarMilageIs0SoChargeShouldBeAlso0(){
        Integer bookedID = 1;
        Integer milage = 0;
        Integer returningCityID = 3;

        when(bookingService.findActiveBookingByID(bookedID)).thenReturn(booked);

        returnCarService.returnCar(bookedID, milage, returningCityID);

        assertEquals(0f,booked.getCharge());
        assertEquals(0, booked.getMilage());
        verify(bookingService,times(0)).changeCarLocalization(booked,returningCityID);
        verify(bookingService, times(1)).closeRentalandSave(booked);
    }
    @Test
    void returnCarExtraCostChargeShouldBe1175(){
        Integer bookedID = 1;
        Integer milage = 350;
        Integer returningCityID = 3;
        Integer rentFee = 1000;

        when(bookingService.findActiveBookingByID(bookedID)).thenReturn(booked);
        when(carService.findRentalFeeByID(booked.getCarId())).thenReturn(rentFee);

        returnCarService.returnCar(bookedID, milage, returningCityID);

        assertEquals(1175f,booked.getCharge());
        assertEquals(350, booked.getMilage());
        verify(bookingService,times(1)).changeCarLocalization(booked,returningCityID);
        verify(bookingService, times(1)).closeRentalandSave(booked);
    }

}