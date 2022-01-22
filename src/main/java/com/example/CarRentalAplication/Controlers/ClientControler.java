package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Services.ClientService;
import com.example.CarRentalAplication.contract.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
public class ClientControler {
    private final ClientService clientService;

    @Autowired
    public ClientControler(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping ("/get")
    public ResponseEntity<ClientDTO> getClientById(@RequestParam(required = false) Integer id) {

        return new ResponseEntity<>(clientService.getByID(id), HttpStatus.OK);
    }

    @PostMapping( "/add")
    public ResponseEntity<String> addClient(@RequestBody ClientDTO clientDTO){

        clientService.saveClient(clientDTO);

        return new ResponseEntity("Saved",HttpStatus.OK);

    }

}
