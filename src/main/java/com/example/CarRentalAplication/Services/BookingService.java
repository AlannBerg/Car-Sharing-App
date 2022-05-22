package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.DateValidator;
import com.example.CarRentalAplication.Exceptions.*;
import com.example.CarRentalAplication.Repositories.BookingRepository;
import com.example.CarRentalAplication.contract.Booked.BookedDTOWithID;
import com.example.CarRentalAplication.contract.Booked.BookedDTO;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapperImpl;
import com.example.CarRentalAplication.models.Booked;
import com.example.CarRentalAplication.models.Car;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BookingService {
    private  CarService carService;
    private  ClientService clientService;
    private  BookingRepository bookingRepository;
    private  CarSharingAppMapperImpl carSharingAppMapper = new CarSharingAppMapperImpl();

    @Autowired
    public BookingService(CarService carService, ClientService clientService, BookingRepository bookingRepository) {
        this.carService = carService;
        this.clientService = clientService;
        this.bookingRepository = bookingRepository;
    }

    @SneakyThrows
    public BookedDTO bookACar(BookedDTO bookingRequest) {

        CarDTO carDTO = carService.findByID(bookingRequest.getCarId());

        // checks if such client exist
        ClientDTO clientDTO = clientService.getByID(bookingRequest.getClientId());


        // searching for active rentals with  specyfic car

        List<Booked> bookedHistoryButEntityList = bookingRepository.findAllActiveBookingsWithThisCar(bookingRequest.getCarId());

        List<BookedDTOWithID> bookingHistory = carSharingAppMapper.bookedTOBookedDTOList(bookedHistoryButEntityList);


        // if we do not have any rentals scheduled that mean all dates are free, and if term is ok we could skip below steps
        if( !bookingHistory.isEmpty() || requestedTermIsNotCorrect(bookingRequest)) {
            // if first date is the same as second or first date is later than second
            if (requestedTermIsNotCorrect(bookingRequest)) {
                throw new RequestedTermIsNotCorrect();
            }
            // if car is not available for some reason
            if (carDTO.getAvailable() == 0) {
                throw new CarNotAvailableException();
            } // rest of  validation
            if (requestedRentDateISTaken(bookingHistory, bookingRequest)) {
                throw new RentalDateForThisCarIsAlreadyTaken();
            }
        }

        bookingRepository.save(carSharingAppMapper.bookedDTOWithNoIDToBooked(bookingRequest));
        return bookingRequest;
    }

    protected boolean requestedRentDateISTaken(List<BookedDTOWithID> bookingHistory, BookedDTO bookingRequest) {

        HashMap<Date,Date> beginANDendOFRent = new HashMap<>();

        //creating a hashmap with <startDate , endDate> of scheduled rentals
        for(BookedDTOWithID bookedDTOWithID : bookingHistory) {

            Date starting = Date.valueOf(bookedDTOWithID.getRentalStartingDate());
            Date ending = Date.valueOf(bookedDTOWithID.getRentalEndDate());

            beginANDendOFRent.put(starting, ending);
        }
        // seting a hashmap with dates to check if requested term will be free
        DateValidator dateValidator = new DateValidator(beginANDendOFRent);

        Date requestedSTART = Date.valueOf(bookingRequest.getRentalStartingDate());
        Date requestedEND = Date.valueOf(bookingRequest.getRentalEndDate());

        // seting start and end of requested term ,method validateDate checks if requested term is free
        Boolean terminIsTaken = dateValidator.validateDate(requestedSTART, requestedEND);

        return terminIsTaken;
        }

    protected boolean requestedTermIsNotCorrect(BookedDTO bookingRequest) {
        Boolean requestTermIsNotCorrect = false;

        Date firstDate = Date.valueOf(bookingRequest.getRentalStartingDate());
        Date secondDate = Date.valueOf(bookingRequest.getRentalEndDate());

        // in case first date and second date are the same
        if(firstDate.compareTo(secondDate) == 0){
            requestTermIsNotCorrect = true;
        }
        // in case first date is later than second date
        if (firstDate.compareTo(secondDate) > 0){
            requestTermIsNotCorrect = true;
        }
        return requestTermIsNotCorrect;
    }

    @SneakyThrows
    public Booked findActiveBookingByID(Integer bookedID) {
        if(bookingRepository.findActiveBookingByID(bookedID).isEmpty()){
            throw new InvalidBookingID();
        }
        return bookingRepository.findActiveBookingByID(bookedID).get(0);
    }

    @SneakyThrows
    public void changeCarLocalization(Booked booked, Integer returningCityID) {

        Car car = carService.findEntityByID(booked.getCarId());

        car.setCurrentLocation(returningCityID);
        carService.updateCar(car);
    }

    public void closeRentalandSave(Booked booked) {
        bookingRepository.update(booked);
    }

    @SneakyThrows
    public List<BookedDTOWithID> findActiveBookingsForClientByClientID(Integer clientID) {

        ClientDTO clientDTO = clientService.getByID(clientID);
        if(clientDTO == null){
            throw new InvalidClientID();
        }

        List<Booked> bookedList = bookingRepository.findAllActiveBookingsForThisClient(clientID);

        return carSharingAppMapper.bookedTOBookedDTOList(bookedList);
    }
}


