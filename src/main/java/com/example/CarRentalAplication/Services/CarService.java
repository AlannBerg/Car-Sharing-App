package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.Repositories.CarRepoEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepoEntityManager carRepoEntityManager;
    private LocalizationService localizationService;


    @Autowired
    public CarService(CarRepoEntityManager carRepoEntityManager, LocalizationService localizationService) {
        this.carRepoEntityManager = carRepoEntityManager;
        this.localizationService = localizationService;
    }

    public List<CarDTO> getCarsEM(){
        return carRepoEntityManager.getCars().stream().
                map(car -> new CarDTO(car.getId(), car.getMake(), car.getModel(), car.getColor(),
                        car.getYear(), car.getHorsepower(), car.getRentfee(), car.getAvailable(),
                        localizationService.getCityName(car.getCurrentLocation())))
                .collect(Collectors.toList());
    }
}
