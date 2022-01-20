package com.example.CarRentalAplication.Repositories;

import com.example.CarRentalAplication.models.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CarRepository {

    private final EntityManager entityManager;
    private final String SELECT = "select car from Car car ";

    public List<Car> getCars(){
        return entityManager.createQuery(SELECT, Car.class).getResultList();
    }

    public Car findByID(Integer id) {
        return entityManager.createQuery(
                SELECT + "WHERE car.id = " + id.toString(),
                Car.class).getResultList().get(0);
    }
}
