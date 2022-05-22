package com.example.CarRentalAplication.contract.Mapper;

import com.example.CarRentalAplication.contract.*;
import com.example.CarRentalAplication.contract.Booked.BookedDTOWithID;
import com.example.CarRentalAplication.contract.Booked.BookedDTO;
import com.example.CarRentalAplication.contract.Booked.ClosedBookingDTO;
import com.example.CarRentalAplication.models.Booked;
import com.example.CarRentalAplication.models.Car;
import com.example.CarRentalAplication.models.Client;
import com.example.CarRentalAplication.models.Localization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class CarSharingAppMapper {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public Client clientDTOtoClient(ClientDTO clientDTO){
        Client client = new Client(
                clientDTO.getName(),
                clientDTO.getLastName(),
                clientDTO.getEmail(),
                Date.valueOf(clientDTO.getBirthday()),
                clientDTO.getPhoneNumber(),
                passwordEncoder.encode(clientDTO.getPassword()),
                "USER",
                (byte) 1);
        return client;
    }

    public ClientDTO clientTOClientDTOtoReturnForUser(Client client){
        ClientDTO clientDTO = new ClientDTO(
                client.getName(),
                client.getLastName(),
                client.getBirthday().toString(),
                client.getEmail(),
                client.getPhoneNumber(),
                "*******");
        return clientDTO;
    }



    @Mapping(source = "id" , target = "bookedID")
    public abstract ClosedBookingDTO bookedToClosedBookingDTO(Booked booked);


    public List<BookedDTOWithID> bookedTOBookedDTOList(List<Booked> bookedList){
        return bookedList.stream().map(
                booked -> new BookedDTOWithID(
                        booked.getId(),
                        booked.getClientId(),
                        booked.getCarId(),
                        booked.getRentalStartingDate().toString(),
                        booked.getRentalEndDate().toString()
                )
        ).collect(Collectors.toList());
    }


    public Booked bookedDTOWithNoIDToBooked(BookedDTO bookedDTO){
        Booked booked = new Booked();
        booked.setClientId(bookedDTO.getClientId());
        booked.setCarId(bookedDTO.getCarId());
        booked.setRentalStartingDate(Date.valueOf(bookedDTO.getRentalStartingDate()));
        booked.setRentalEndDate(Date.valueOf(bookedDTO.getRentalEndDate()));
        return booked;
    }


    public Booked bookedDTOToBooked(BookedDTOWithID bookedDTOWithID){
        Booked booked = new Booked();
        booked.setClientId(bookedDTOWithID.getClientId());
        booked.setCarId(bookedDTOWithID.getCarId());
        booked.setRentalStartingDate(Date.valueOf(bookedDTOWithID.getRentalStartingDate()));
        booked.setRentalEndDate(Date.valueOf(bookedDTOWithID.getRentalEndDate()));
        return booked;
    }

    @Mapping(target = "Car.id" , ignore = true)
    public abstract CarDTO carTOCarDTO(Car car);

    @Mapping(target = "Car.id" , ignore = true)
    public abstract List<CarDTO> carTOCarDTOlist(List<Car> car);


    public abstract ClientDTO unregisteredClientToClientDTO(UnregisteredClientDTO unregisteredClientDTO);

    @Mapping(target = "Localization.id" , ignore = true)
    public abstract List<LocalizationDTO> localizationTOLocalizationDTOList(List<Localization> localizationList);
}
