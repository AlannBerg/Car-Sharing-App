package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Repositories.LocalizationRepository;
import com.example.CarRentalAplication.contract.LocalizationDTO;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapperImpl;
import com.example.CarRentalAplication.models.Localization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizationService {
    private LocalizationRepository localizationRepository;
    private CarSharingAppMapperImpl carSharingAppMapper = new CarSharingAppMapperImpl();

    @Autowired
    public LocalizationService(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    public String getCityName(Integer currentLocation) {
        return localizationRepository.findCityByID(currentLocation).getCity();
    }

    public List<LocalizationDTO> getLocalizations() {


        List<Localization> localizationList = localizationRepository.findAll();

        return carSharingAppMapper.localizationTOLocalizationDTOList(localizationList);

    }


}
