package com.example.CarRentalAplication.Repositories;

import com.example.CarRentalAplication.contract.LocalizationDTO;
import com.example.CarRentalAplication.models.Localization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class LocalizationRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LocalizationRepository localizationRepository;


    private final Localization localization = new Localization(
            1,
            "city",
            "postalCode",
            "street",
            "localNumb"
    );

    // some error occurs , gona fix it later
    @Test
    void findCityByID() {
        Integer cityId = 1;

        entityManager.persist(localization);
        assertEquals(localization,localizationRepository.findCityByID(cityId));
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }
}