package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.EmailAlreadyExist;
import com.example.CarRentalAplication.Exceptions.InvalidClientID;
import com.example.CarRentalAplication.Repositories.ClientRepository;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapper;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapperImpl;
import com.example.CarRentalAplication.models.Client;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private CarSharingAppMapper carSharingAppMapper = new CarSharingAppMapperImpl();

    private  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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


        return carSharingAppMapper.clientTOClientDTOtoReturnForUser(client);


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


        Client client = carSharingAppMapper.clientDTOtoClient(clientDTO);

        clientRepository.saveClient(client);
    }

    protected boolean emailExist(String email) {

        List<Client> clients = clientRepository.findByEmail(email);
        if(clients.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

}
