package com.example.gestionb2c.controller;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @PostMapping(path = "/save")
    private ResponseEntity<List<Client>> addNewClient(@RequestBody List<Client> client){
        if (client != null) {
            List<Client> newClient = clientService.saveClients(client);
            return ResponseEntity.ok().body(newClient);
        }
        return ResponseEntity.status(HttpStatus.valueOf(505)).body(null);
    }



}
