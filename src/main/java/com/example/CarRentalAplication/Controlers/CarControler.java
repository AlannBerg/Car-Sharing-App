package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.QueryBuilder.Querybuilder;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cars")
public class CarControler {
    private final CarService carService;

    @Autowired
    public CarControler(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getCars")
    public ResponseEntity<List<CarDTO>> getCars(@RequestParam("localizationID")final Optional<Integer> localizationID,
                                                @RequestParam("priceFrom") final Optional<Integer> priceFrom,
                                                @RequestParam("priceTo") final Optional<Integer> priceTo,
                                                @RequestParam("hpowerFrom") final Optional<Integer> hpowerFrom,
                                                @RequestParam("hpowerTo") final Optional<Integer> hpowerTo){

        String query = Querybuilder.buildQuery(localizationID, priceFrom, priceTo, hpowerFrom, hpowerTo);

        return new ResponseEntity<>(carService.getCars(query), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO){
        return new ResponseEntity<>(carService.addCar(carDTO),HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCar(@RequestParam Integer carID){

        return new ResponseEntity<>(carService.delete(carID),HttpStatus.OK);

    }

}
