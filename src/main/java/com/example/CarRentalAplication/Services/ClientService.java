package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Controlers.Exceptions.InvalidClientID;
import com.example.CarRentalAplication.Repositories.ClientRepository;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.models.Client;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @SneakyThrows
    public ClientDTO getByID(Integer id) {

        Client client;
        if(clientRepository.findByID(id).isEmpty()){
            throw new InvalidClientID();
        }else {
            client = clientRepository.findByID(id).get(0);
        }

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
