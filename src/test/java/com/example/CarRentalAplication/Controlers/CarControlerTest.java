package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.QueryBuilder.Querybuilder;
import com.example.CarRentalAplication.Services.CarService;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.models.Car;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarControler.class)
@AutoConfigureMockMvc()
class CarControlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @MockBean
    private Querybuilder querybuilder;

    private final CarDTO carDTO = new CarDTO(
            "make",
            "model",
            "color",
            2022,
            100,
            500,
            (byte)1,
            "1"
    );

    @WithMockUser(username = "admin",roles = {"ADMIN"})
    @Test
    void getCars() throws Exception {
        String uri = "/cars/getCars";


        List<CarDTO> selectedCars = List.of(carDTO);

        RequestBuilder request = MockMvcRequestBuilders.get(uri)
                .param("localizationID","1")
                .param("priceFrom","1")
                .param("priceTo","1")
                .param("hpowerFrom" , "1")
                .param("hpowerTo" , "1")
                .accept(MediaType.APPLICATION_JSON);


        when(carService.getCars(any())).thenReturn(selectedCars);

        String result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals("[{\"make\":\"make\",\"model\":\"model\",\"color\":\"color\",\"year\":2022,\"horsepower\":100,\"rentfee\":500,\"available\":true,\"currentLocation\":\"1\"}]",
                result);
    }
}