package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Services.ClientService;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.contract.ClientsecurityDTO;
import com.example.CarRentalAplication.contract.UnregisteredClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/client")
public class ClientControler {
    private final ClientService clientService;

    public ClientControler(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping ("/get")
    public ResponseEntity<ClientDTO> getClientById(@RequestParam(required = false) Integer id) {

        return new ResponseEntity<>(clientService.getByID(id), HttpStatus.OK);
    }



    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody UnregisteredClientDTO unregisteredClientDTO){

        ClientDTO clientDTO = new ClientDTO(
                unregisteredClientDTO.getName(),
                unregisteredClientDTO.getLastName(),
                unregisteredClientDTO.getBirthday(),
                unregisteredClientDTO.getEmail(),
                unregisteredClientDTO.getPhoneNumber(),
                unregisteredClientDTO.getPassword()
        );

        clientService.registerNewClient(clientDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
