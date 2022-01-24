package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Services.LocalizationService;

import com.example.CarRentalAplication.contract.LocalizationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/localization")
public class LocalizationControler {
    private final LocalizationService localizationService;

    @Autowired
    public LocalizationControler(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @GetMapping("/getLocalizations")
    public ResponseEntity<List<LocalizationDTO>> getLocalizations(){
        return new ResponseEntity<>(localizationService.getLocalizations(), HttpStatus.OK);
    }

}
