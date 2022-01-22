package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Repositories.LocalizationRepository;
import com.example.CarRentalAplication.models.Localization;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalizationServiceTest {

    @Mock
    private LocalizationRepository localizationRepository;

    private LocalizationService localizationService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationService(localizationRepository);
    }

    @Test
    void canGetCityByID() {
        Integer id = 2;
        Localization localization = new Localization();
        when(localizationRepository.findCityByID(id)).thenReturn(localization);
        localizationService.getCityName(id);
        verify(localizationRepository).findCityByID(id);
    }

    @Test
    void canGetAllLocalizationsTest() {
        Localization localization = new Localization(
                1,
                "city",
                "postalCode",
                "street",
                "localNumb"
        );

        when(localizationRepository.findAll()).thenReturn(List.of(localization));

        localizationService.getLocalizations();

        verify(localizationRepository).findAll();
        assertEquals( 1,localizationService.getLocalizations().size());

    }
}