package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.InvalidCarID;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.Repositories.CarRepository;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapper;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapperImpl;
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
    private CarSharingAppMapperImpl carSharingAppMapper;


    @Autowired
    public CarService(CarRepository carRepository, LocalizationService localizationService) {
        this.carRepository = carRepository;
        this.localizationService = localizationService;
        this.carSharingAppMapper = new CarSharingAppMapperImpl();
    }

    public List<CarDTO> getCars(String query){

        List<Car> carFromDB =  carRepository.getCarsUsingQuery(query);

        return carSharingAppMapper.carTOCarDTOlist(carFromDB);

    }

    @SneakyThrows
    public CarDTO findByID(Integer carId) {
        Car car = carRepository.findByID(carId);
        if(car == null){
            throw new InvalidCarID();
        }

        return carSharingAppMapper.carTOCarDTO(car);
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
