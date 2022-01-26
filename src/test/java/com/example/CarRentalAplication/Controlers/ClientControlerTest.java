package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Security.MyUserDetailService;
import com.example.CarRentalAplication.Services.ClientService;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.contract.UnregisteredClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientControler.class)
@AutoConfigureMockMvc(addFilters = false)
class ClientControlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MyUserDetailService myUserDetailService;

    @MockBean
    private ClientService clientService;

    private ClientDTO clientDTO =new ClientDTO(
            "1",
            "1",
            "1",
            "1",
            "1",
            "1");

    private UnregisteredClientDTO unregisteredClientDTO = new UnregisteredClientDTO(
            "1",
            "1",
            "1",
            "1",
            "1",
            "1");

    @Test
    void addClienttestshouldAddClient() throws Exception {
        String uri = "/client/register";


        RequestBuilder request = MockMvcRequestBuilders.post(uri)
                .content(asJsonString(unregisteredClientDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        mvc.perform(request).andExpect(status().isOk());


        ArgumentCaptor<ClientDTO> argumentCaptor = ArgumentCaptor.forClass(ClientDTO.class);

        verify(clientService).registerNewClient(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getName(), clientDTO.getName());
        assertEquals(argumentCaptor.getValue().getBirthday(), clientDTO.getBirthday());
        assertEquals(argumentCaptor.getValue().getEmail(), clientDTO.getEmail());

    }

    @Test
    void getClientByIDShouldReturnFirstClient() throws Exception {
        String uri = "/client/get";
        Integer clientID = 1;

        RequestBuilder request = MockMvcRequestBuilders.get(uri)
                .param("id","1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        when(clientService.getByID(clientID)).thenReturn(clientDTO);

        String result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        assertEquals("{\"name\":\"1\",\"lastName\":\"1\",\"birthday\":\"1\",\"email\":\"1\",\"phoneNumber\":\"1\",\"password\":\"1\",\"role\":null,\"active\":null}", result);

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}