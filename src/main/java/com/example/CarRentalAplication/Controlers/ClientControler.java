package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Security.MyUserDetailService;
import com.example.CarRentalAplication.Services.ClientService;
import com.example.CarRentalAplication.Services.TEMPSERVICE;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.contract.ClientsecurityDTO;
import com.example.CarRentalAplication.models.Clientsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/client")
public class ClientControler {
    private final ClientService clientService;

    @Autowired
    private final TEMPSERVICE tempservice;

    public ClientControler(ClientService clientService, TEMPSERVICE tempservice) {
        this.clientService = clientService;
        this.tempservice = tempservice;
    }

    @GetMapping ("/get")
    public ResponseEntity<ClientDTO> getClientById(@RequestParam(required = false) Integer id) {

        return new ResponseEntity<>(clientService.getByID(id), HttpStatus.OK);
    }

    @PostMapping( "/add")
    public ResponseEntity<String> addClient(@RequestBody ClientDTO clientDTO){

        clientService.saveClient(clientDTO);

        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody ClientsecurityDTO clientsecurity){

        tempservice.registerNewClient(clientsecurity);
        return new ResponseEntity(HttpStatus.OK);
    }

}
