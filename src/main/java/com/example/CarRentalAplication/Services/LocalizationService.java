package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.InvalidLocalizationID;
import com.example.CarRentalAplication.Repositories.LocalizationRepository;
import com.example.CarRentalAplication.contract.LocalizationDTO;
import com.example.CarRentalAplication.models.Localization;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizationService {
    private LocalizationRepository localizationRepository;

    @Autowired
    public LocalizationService(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    public String getCityName(Integer currentLocation) {
        return localizationRepository.findCityByID(currentLocation).getCity();
    }

    public List<LocalizationDTO> getLocalizations() {
        return localizationRepository.findAll().stream().map(localization -> new LocalizationDTO(
                localization.getCity(),
                localization.getPostalCode(),
                localization.getStreet(),
                localization.getLocalNumber()
        )).collect(Collectors.toList());
    }


}
