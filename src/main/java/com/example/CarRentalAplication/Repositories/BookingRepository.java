package com.example.CarRentalAplication.Repositories;

import com.example.CarRentalAplication.models.Booked;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookingRepository {

    private final EntityManager entityManager;


    public List<Booked> findAllActiveBookingsWithThisCar(Integer carId) {
        return entityManager.createQuery(
                "SELECT booked FROM Booked booked where booked.carId = "+
                        carId.toString()+
                        " and booked.charge is null "
                        ,Booked.class).getResultList();


    }

    public void save(Booked bookingRequest) {
        entityManager.persist(bookingRequest);
    }

    public List<Booked> findActiveBookingByID(Integer bookedID) {
        return entityManager.createQuery(
                "SELECT booked FROM Booked booked where booked.id = " + bookedID +
                " and  booked.charge is null ",
                Booked.class).getResultList();
    }

    public void update(Booked booked) {
        entityManager.merge(booked);
    }

    public List<Booked> findAllActiveBookingsForThisClient(Integer clientID) {
        return entityManager.createQuery(
                "SELECT booked FROM Booked booked where booked.clientId = " + clientID +
                        " and booked.charge is null ", Booked.class)
                        .getResultList();
    }
}
