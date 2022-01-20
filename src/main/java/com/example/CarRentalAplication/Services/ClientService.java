package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Repositories.ClientRepository;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public ClientDTO getByID(Integer id) {

        Client client = clientRepository.findByID(id);

        return new ClientDTO(
                client.getName(),
                client.getLastName(),
                client.getBirthday().toString(),
                client.getEmail(),
                client.getPhoneNumber());
    }

    public void saveClient(ClientDTO clientDTO) {
        Client client = clientDTO.dtoTOentity();
        clientRepository.saveClient(client);
    }

}
