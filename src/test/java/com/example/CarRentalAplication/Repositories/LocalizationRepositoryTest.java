package com.example.CarRentalAplication.Repositories;

import com.example.CarRentalAplication.Services.LocalizationService;
import com.example.CarRentalAplication.contract.LocalizationDTO;
import com.example.CarRentalAplication.models.Localization;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@DataJpaTest
class LocalizationRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private LocalizationRepository localizationRepository;

    @MockBean
    private LocalizationService localizationService;


    private final Localization localization = new Localization(
            1,
            "city",
            "postalCode",
            "street",
            "localNumb"
    );
    private final Localization localization2 = new Localization(
            2,
            "city2",
            "postalCod2",
            "street2",
            "localNumb2"
    );

    @Test
    void findAllshouldReturn2Localizations() {
        testEntityManager.persist(localization);
        testEntityManager.persist(localization2);

        testEntityManager.flush();

        List<Localization> returned = localizationRepository.findAll();

        assertEquals(2, returned.size());

    }

}