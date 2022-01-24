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

    public List<Car> getCars(){
        return entityManager.createQuery(
                "select car from Car car", Car.class)
                .getResultList();
    }

    public Car findByID(Integer id) {
        return entityManager.createQuery(
                "select car from Car car WHERE car.id = " + id.toString(), Car.class)
                .getResultList().get(0);
    }

    public void updateCar(Car car) {
        entityManager.merge(car);
    }

    public List<Car> getCarsUsingQuery(String query) {
        return entityManager.createQuery(query,Car.class).getResultList();
    }
}
