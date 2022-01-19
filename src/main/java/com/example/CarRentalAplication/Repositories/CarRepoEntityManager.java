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
public class CarRepoEntityManager {

    private final EntityManager entityManager;

    public List<Car> getCars(){
        return entityManager.createQuery("select car from Car car", Car.class).getResultList();
    }
}
