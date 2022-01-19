package com.example.CarRentalAplication.Repositories;

import com.example.CarRentalAplication.models.Localization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends CrudRepository<Localization, Integer> {

    @Query("SELECT l FROM Localization l WHERE l.id = :id")
    Localization findCityByID(Integer id);
}
