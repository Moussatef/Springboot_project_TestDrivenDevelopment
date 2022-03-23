package com.example.gestionb2c.controller;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
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

    @PutMapping (path = "/update")
    public ResponseEntity<Client> updateClient( @RequestBody Client client){
        Optional.ofNullable(client).orElseThrow(() -> new IllegalStateException("Client is null"));
        Client updatedClient = Optional.ofNullable( clientService.updateClient(client)).orElseThrow(() ->
            new IllegalStateException("Update Client filed!! ")
        );
        return ResponseEntity.ok().header("Updated","Client updated successfully").body(updatedClient);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id){
        Client client = Optional.ofNullable(clientService.getClient(id)).orElseThrow( () -> new IllegalStateException("Client :"+id+" not found") );
        return ResponseEntity.ok().body(client);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable("email") String email){
        Client client = Optional.ofNullable(clientService.getClientByEmail(email)).orElseThrow( () -> new IllegalStateException("Client :"+email+" not found") );
        return ResponseEntity.ok().body(client);
    }

    @GetMapping(path = "/gander/{gander}")
    public ResponseEntity<List<Client>> getClientByGander(@PathVariable("gander") String gander){

        List<Client> client = Optional.ofNullable(clientService.getListClientByGander(Gander.valueOf(gander))).orElseThrow( () -> new IllegalStateException("Client :"+gander+" not found") );
        return ResponseEntity.ok().body(client);
    }



}
