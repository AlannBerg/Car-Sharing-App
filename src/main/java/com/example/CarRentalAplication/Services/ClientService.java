package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.EmailAlreadyExist;
import com.example.CarRentalAplication.Exceptions.InvalidClientID;
import com.example.CarRentalAplication.Repositories.ClientRepository;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.models.Client;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
                client.getPhoneNumber(),
                "*******");
    }

    public void saveClient(ClientDTO clientDTO) {
        Client client = clientDTO.dtoTOentity();
        clientRepository.saveClient(client);
    }

    @SneakyThrows
    public void registerNewClient(ClientDTO clientDTO){
        if (emailExist(clientDTO.getEmail())) {
            throw new EmailAlreadyExist();
        }

        Client client = new Client(
                clientDTO.getName(),
                clientDTO.getLastName(),
                clientDTO.getEmail(),
                Date.valueOf(clientDTO.getBirthday()),
                clientDTO.getPhoneNumber(),
                passwordEncoder.encode(clientDTO.getPassword()),
                "USER",
                (byte) 1);

        clientRepository.saveClient(client);
    }

    private boolean emailExist(String email) {

        List<Client> clients = clientRepository.findByEmail(email);
        return ! clients.isEmpty();
    }

}
