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
import java.util.Locale;

@RestController
@RequestMapping(path = "api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @PostMapping(path = "/save")
    public ResponseEntity<List<Client>> addNewClient(@RequestBody List<Client> client){
        try {
            if (!client.isEmpty()) {

                List<Client> newClients = clientService.saveClients(client);

                if(newClients == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok().body(newClients);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Test","Test").build();
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Exception",e.getMessage().toUpperCase(Locale.ROOT)).build();
        }

    }



}
