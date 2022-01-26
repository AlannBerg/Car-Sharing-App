package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Security.MyUserDetailService;
import com.example.CarRentalAplication.Services.BookingService;
import com.example.CarRentalAplication.Services.ReturnCarService;
import com.example.CarRentalAplication.contract.BookedDTO;
import com.example.CarRentalAplication.models.Booked;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookingControler.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingControlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private MyUserDetailService myUserDetailService;

    @MockBean
    private ReturnCarService returnCarService;

    private BookedDTO bookedDTO = new BookedDTO(
            1,
            1,
            "today",
            "tomorow");


    @Test
    void showAllActiveBookingsForClientshouldReturn1booking() throws Exception {
        String uri = "/booking/getbookings";

        List<BookedDTO> bookedList = List.of(bookedDTO);

        when(bookingService.findActiveBookingsForClientByClientID(1)).thenReturn(bookedList);

        RequestBuilder request = MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON).param("clientID","1");

        String result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals("[{\"clientId\":1,\"carId\":1,\"rentalStartingDate\":\"today\",\"rentalEndDate\":\"tomorow\",\"milage\":null,\"charge\":null}]",
                result);

    }
    @Test
    void bookACartest() throws Exception {
        String uri = "/booking/bookCar";

        when(bookingService.bookACar(any())).thenReturn(bookedDTO);

        RequestBuilder request = MockMvcRequestBuilders.post(uri)
                .param("clientId" , "1")
                .param("carId", "1")
                .param("rentalStartingDate", "today")
                .param("rentalEndDate", "tomorow")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        String result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        assertEquals("{\"clientId\":1,\"carId\":1,\"rentalStartingDate\":\"today\",\"rentalEndDate\":\"tomorow\",\"milage\":null,\"charge\":null}"
                , result );
    }

    @Test
    void returnACartest() throws Exception {
        String uri = "/booking/return";

        when(returnCarService.returnCar(1,1,1)).thenReturn(bookedDTO);

        RequestBuilder request = MockMvcRequestBuilders.post(uri)
                .param("bookedID","1")
                .param("milage","1")
                .param("returningCityID","1")
                .contentType(MediaType.APPLICATION_JSON);

        String result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals("{\"clientId\":1,\"carId\":1,\"rentalStartingDate\":\"today\",\"rentalEndDate\":\"tomorow\",\"milage\":null,\"charge\":null}",
                result);
    }
}