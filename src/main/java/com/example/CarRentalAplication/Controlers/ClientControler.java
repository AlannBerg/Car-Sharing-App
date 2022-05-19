package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Services.ClientService;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.contract.ClientsecurityDTO;
import com.example.CarRentalAplication.contract.Mapper.CarSharingAppMapperImpl;
import com.example.CarRentalAplication.contract.UnregisteredClientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/client")
public class ClientControler {
    private final ClientService clientService;
    private final CarSharingAppMapperImpl carSharingMapper = new CarSharingAppMapperImpl();

    public ClientControler(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping ("/get")
    public ResponseEntity<ClientDTO> getClientById(@RequestParam(required = false) Integer id) {

        return new ResponseEntity<>(clientService.getByID(id), HttpStatus.OK);
    }



    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody UnregisteredClientDTO unregisteredClientDTO){


        ClientDTO clientDTO = carSharingMapper.unregisteredClientToClientDTO(unregisteredClientDTO);

        clientService.registerNewClient(clientDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
