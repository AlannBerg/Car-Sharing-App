package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.QueryBuilder.Querybuilder;
import com.example.CarRentalAplication.Security.MyUserDetailService;
import com.example.CarRentalAplication.Services.CarService;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.models.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarControler.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @MockBean
    private MyUserDetailService myUserDetailService;

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

    @Test
    void getCarstest() throws Exception {
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

        assertEquals("[{\"make\":\"make\",\"model\":\"model\",\"color\":\"color\",\"year\":2022,\"horsepower\":100,\"rentfee\":500,\"available\":1,\"currentLocation\":\"1\"}]",
                result);
    }

    @Test
    void deteleCarTest() throws Exception {
        String uri = "/cars/delete";
        String carID = "1";

        RequestBuilder request = MockMvcRequestBuilders.delete(uri)
                .param("carID", carID)
                .accept(MediaType.APPLICATION_JSON);

        when(carService.delete(Integer.valueOf(carID))).thenReturn(any());

        mvc.perform(request).andExpect(status().isOk());
        verify(carService).delete(any());
    }


    @Test
    void addCarTest() throws Exception {
        String uri = "/cars/add";

        RequestBuilder request = MockMvcRequestBuilders.post(uri)
                .content(asJsonString(carDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        when(carService.addCar(any())).thenReturn(carDTO);

        String response = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ArgumentCaptor<CarDTO> argumentCaptor = ArgumentCaptor.forClass(CarDTO.class);

        verify(carService).addCar(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().getMake(),carDTO.getMake());
        assertEquals("{\"make\":\"make\",\"model\":\"model\",\"color\":\"color\",\"year\":2022,\"horsepower\":100,\"rentfee\":500,\"available\":1,\"currentLocation\":\"1\"}",response);

    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}