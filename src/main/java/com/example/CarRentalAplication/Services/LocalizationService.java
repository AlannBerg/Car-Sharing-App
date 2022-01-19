package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Repositories.LocalizationRepository;
import com.example.CarRentalAplication.models.Localization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizationService {
    private LocalizationRepository localizationRepository;

    @Autowired
    public LocalizationService(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    public String getCityName(Integer currentLocation) {
        Localization localization = localizationRepository.findCityByID(currentLocation);
        return localization.getCity();
    }
}
