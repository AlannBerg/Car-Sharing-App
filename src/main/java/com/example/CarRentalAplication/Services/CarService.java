package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.InvalidCarID;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.Repositories.CarRepository;
import com.example.CarRentalAplication.models.Car;
import com.example.CarRentalAplication.models.Localization;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;
    private LocalizationService localizationService;


    @Autowired
    public CarService(CarRepository carRepository, LocalizationService localizationService) {
        this.carRepository = carRepository;
        this.localizationService = localizationService;
    }

    public List<CarDTO> getCars(String query){
        return carRepository.getCarsUsingQuery(query).stream().
                map(car -> new CarDTO(car.getMake(), car.getModel(), car.getColor(),
                        car.getYear(), car.getHorsepower(), car.getRentfee(), car.getAvailable(),
                        localizationService.getCityName(car.getCurrentLocation())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public CarDTO findByID(Integer carId) {
        Car car = carRepository.findByID(carId);
        if(car == null){
            throw new InvalidCarID();
        }
        return new CarDTO(car.getMake(), car.getModel(), car.getColor(), car.getYear(), car.getHorsepower(),
                            car.getRentfee(), car.getAvailable(), localizationService.getCityName(car.getCurrentLocation()));
    }

    public Integer findRentalFeeByID(Integer carId) {
        CarDTO carDTO = findByID(carId);
        return carDTO.getRentfee();
    }

    @SneakyThrows
    public Car findEntityByID(Integer carId) {
        Car car = carRepository.findByID(carId);
        if(car == null){
            throw new InvalidCarID();
        }else {
            return car;
        }
    }

    public void updateCar(Car car) {
        carRepository.updateCar(car);
    }

    public CarDTO addCar(CarDTO carDTO) {
        carRepository.addCar(carDTO.dtoToEntity());
        return carDTO;
    }

    @SneakyThrows
    public String delete(Integer carID) {

        Car car = carRepository.findByID(carID);

        if(car == null){
            throw new InvalidCarID();
        }
        carRepository.delete(car);

        return "Deleted";
    }
}
