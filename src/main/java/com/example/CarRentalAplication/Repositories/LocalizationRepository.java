package com.example.CarRentalAplication.Repositories;

import com.example.CarRentalAplication.models.Localization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class LocalizationRepository {
    private final EntityManager entityManager;
    private String selectAll = "SELECT l FROM Localization l";

    public Localization findCityByID(Integer id){
        return entityManager.createQuery(
                selectAll + " WHERE l.id =" + id.toString(),
                Localization.class).getResultList().get(0);
    }

    public List<Localization> findAll(){
        return entityManager.createQuery(selectAll,Localization.class).getResultList();
    }

}
