package com.example.gestionb2c.controller;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @PostMapping(path = "/save")
    public ResponseEntity<String> addNewClient(@RequestBody List<Client> client){
        try {
            if (!client.isEmpty()) {

                List<Client> newClients = clientService.saveClients(client);

                if(newClients == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok().body("Client Inserted");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Test","Test").build();
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Exception",e.getMessage().toUpperCase(Locale.ROOT)).build();
        }
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Client>> getClients(){
        return !clientService.getListClients().isEmpty() ? ResponseEntity.ok().body(clientService.getListClients()) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping (path = "/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
        Optional.ofNullable(client).orElseThrow(() -> new IllegalStateException("Client is null"));
        Client updatedClient = Optional.ofNullable( clientService.updateClient(client)).orElseThrow(() ->
            new IllegalStateException("Update Client filed!! ")
        );
        return ResponseEntity.ok().header("Updated","Client updated successfully").body(updatedClient);
    }



}
