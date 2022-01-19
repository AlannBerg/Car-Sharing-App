package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarControler {
    private CarService carService;

    @Autowired
    public CarControler(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getCars")
    public ResponseEntity<List<CarDTO>> getCars(){
        return new ResponseEntity<>(carService.getCarsEM(), HttpStatus.OK);
    }
}
