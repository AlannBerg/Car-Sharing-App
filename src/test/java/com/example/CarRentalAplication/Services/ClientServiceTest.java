package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.InvalidClientID;
import com.example.CarRentalAplication.Repositories.ClientRepository;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.models.Client;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.crypto.Data;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientService clientService;

    private final Client client = new Client(
            null,
            "name",
            "lastName",
            Date.valueOf("2000-01-01"),
            "mail",
            "123456789");

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientRepository);
    }

    @Test
    void getingClientWithCorrectIDshouldBeOk() {
        // given
        Integer id = 99;

        // wheb
        when(clientRepository.findByID(id)).thenReturn(List.of(client));

        clientService.getByID(id);
        //then


        verify(clientRepository, times(2)).findByID(id);
        assertEquals(client ,clientRepository.findByID(id).get(0));

    }
    @Test
    void getingClientWithInvalidIDshouldThrowInvalidClientIdException() {
        Integer id = 99;

        when(clientRepository.findByID(id)).thenReturn(List.of());

        assertThrows( InvalidClientID.class,() ->  clientService.getByID(id));
    }

    @Test
    void saveClientDtoshouldSaveClient() {
        // given

        ClientDTO clientDTO = new ClientDTO(
                "name",
                "lastName",
                "2000-01-01",
                "mail",
                "123456789"
        );
        // when
        clientService.saveClient(clientDTO);

        //then
        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);

        verify(clientRepository).saveClient(clientArgumentCaptor.capture());

        Client captutedClient = clientArgumentCaptor.getValue();

        assertThat(captutedClient.getName()).isEqualTo(client.getName());
        assertThat(captutedClient.getLastName()).isEqualTo(client.getLastName());
        assertThat(captutedClient.getBirthday()).isEqualTo(client.getBirthday());
        assertThat(captutedClient.getEmail()).isEqualTo(client.getEmail());
        assertThat(captutedClient.getPhoneNumber()).isEqualTo(client.getPhoneNumber());
    }
}