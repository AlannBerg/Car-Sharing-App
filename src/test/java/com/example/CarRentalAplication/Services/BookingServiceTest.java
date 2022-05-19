package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.CarNotAvailableException;
import com.example.CarRentalAplication.Exceptions.InvalidBookingID;
import com.example.CarRentalAplication.Exceptions.InvalidClientID;
import com.example.CarRentalAplication.Repositories.BookingRepository;
import com.example.CarRentalAplication.contract.BookedDTO.BookedDTO;
import com.example.CarRentalAplication.contract.BookedDTO.BookedDTOWithNoID;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.models.Booked;
import com.example.CarRentalAplication.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private CarService carService;
    @Mock
    private ClientService clientService;
    @Mock
    private BookingRepository bookingRepository;

    private BookingService bookingService;

    private  BookedDTO bookedDTO = new BookedDTO(
            1,
            1,
            1,
            "2000-01-01",
            "2000-02-02"
    );

    private  BookedDTOWithNoID bookedDTOwithnoid = new BookedDTOWithNoID(
            1,
            1,
            "2000-01-01",
            "2000-02-02"
    );
    private final Booked booked = new Booked(
            1,
            1,
            1,
            Date.valueOf("2000-01-01"),
            Date.valueOf("2000-02-02")
    );

    private  ClientDTO clientDTO = new ClientDTO(
            "name",
            "lastName",
            "2000-01-01",
            "mail",
            "123456789",
            "1"
    );

    private final CarDTO carDTO = new CarDTO(
            "make",
            "model",
            "color",
            2022,
            100,
            100,
            (byte)1,
            "1"
    );

    @BeforeEach
    void setUp() {
        bookingService = new BookingService(carService,clientService,bookingRepository);
    }

    @Test
    void bookACarShouldBookACar() {
        Integer carID = 1;
        //when
        when(carService.findByID(bookedDTO.getCarId())).thenReturn(carDTO);
        when(clientService.getByID(bookedDTO.getClientId())).thenReturn(clientDTO);
        when(bookingRepository.findAllActiveBookingsWithThisCar(carID)).thenReturn(List.of());
        bookingService.bookACar(bookedDTOwithnoid);

        //then

        assertEquals(0,bookingRepository.findAllActiveBookingsWithThisCar(bookedDTO.getCarId()).size());

    }

    @Test
    void bookACarIfCarNotAvailableThrowExcepion() {
        Integer carID = 1;
         CarDTO notAvailablecarDTo = new CarDTO(
                "make",
                "model",
                "color",
                2022,
                100,
                100,
                 (byte)0,           // not available
                "1"
        );
        when(carService.findByID(bookedDTO.getCarId())).thenReturn(notAvailablecarDTo);
        when(clientService.getByID(bookedDTO.getClientId())).thenReturn(clientDTO);
        when(bookingRepository.findAllActiveBookingsWithThisCar(carID)).thenReturn(List.of(booked));

        assertThrows(CarNotAvailableException.class ,()-> bookingService.bookACar(bookedDTOwithnoid));

    }

    @Test
    void requestRentDateIsTakenShouldReturnTrueSameTerms(){
        List<BookedDTO> bookingHistory = List.of(bookedDTO);

        //same terms here so should return true
        assertTrue(bookingService.requestedRentDateISTaken(bookingHistory,bookedDTOwithnoid));
    }

    @Test
    void requestRentDateIsTakenshouldReturnFalseDifferentTerms(){
        List<BookedDTO> bookingHistory = List.of(new BookedDTO(
                1,
                1,
                1,
                "2000-02-10",
                "2000-02-15"
        ));

        assertFalse(bookingService.requestedRentDateISTaken(bookingHistory,bookedDTOwithnoid));
    }

    @Test
    void requestedTermIsNotCorrectTwoDatesAreTheSameShouldReturnTrue(){
        BookedDTOWithNoID badRequestToDatesTheSame = new BookedDTOWithNoID(
                1,
                1,
                "2000-01-01",
                "2000-01-01"
        );

        assertTrue(bookingService.requestedTermIsNotCorrect(badRequestToDatesTheSame));
    }
    @Test
    void requestedTermIsNotCorrectFirstDateIsAfterSecondShouldReturnTrue(){
        BookedDTOWithNoID badRequestFirstDateIsLater = new BookedDTOWithNoID(

                1,
                1,
                "2000-01-03",
                "2000-01-01"
        );
        assertTrue(bookingService.requestedTermIsNotCorrect(badRequestFirstDateIsLater));
    }
    @Test
    void requestedTermIsNotCorrectGoodDateShouldReturnFalse(){

        assertFalse(bookingService.requestedTermIsNotCorrect(bookedDTOwithnoid));
    }
    @Test
    void findActiveBookingByIDshouldReturnBooking() {
        Integer bookingID = 1;

        when(bookingRepository.findActiveBookingByID(bookingID)).thenReturn(List.of(booked));
        bookingService.findActiveBookingByID(1);

        verify(bookingRepository,times(2)).findActiveBookingByID(bookingID);
        assertEquals(booked, bookingService.findActiveBookingByID(bookingID));
    }

    @Test
    void findActiveBookingByIDInvalidIDShouldThrownException() {
        Integer bookingID = 1;

        when(bookingRepository.findActiveBookingByID(bookingID)).thenReturn(List.of());

        assertThrows(InvalidBookingID.class, ()->bookingService.findActiveBookingByID(1));
        verify(bookingRepository,times(1)).findActiveBookingByID(bookingID);
    }

    @Test
    void changeCarLocalizationTest() {
        Car car = new Car(
                1,
                "make",
                "model",
                "color",
                2022,
                100,
                100,
                (byte)1,
                1
        );
        Integer returningCityID = 1;

        when(carService.findEntityByID(booked.getCarId())).thenReturn(car);

        bookingService.changeCarLocalization(booked,returningCityID);

        verify(carService, times(1)).findEntityByID(booked.getCarId());
        verify(carService).updateCar(car);

    }

    @Test
    void closeRentalandSaveTest() {

        bookingService.closeRentalandSave(booked);
        verify(bookingRepository).update(booked);
    }

    @Test
    void findActiveBookingsForClientByClientIDtest() {
        Integer clientID = 1;

        when(clientService.getByID(clientID)).thenReturn(clientDTO);

        bookingService.findActiveBookingsForClientByClientID(clientID);

        verify(bookingRepository,times(1)).findAllActiveBookingsForThisClient(clientID);
    }
    @Test
    void findActiveBookingsForClientByClientIDInvalidClientIDShouldThrowException() {
        Integer clientID = 1;

        when(clientService.getByID(clientID)).thenReturn(null);

        assertThrows(InvalidClientID.class , ()->bookingService.findActiveBookingsForClientByClientID(clientID));

        verify(bookingRepository,times(0)).findAllActiveBookingsForThisClient(clientID);
    }
}