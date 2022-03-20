package com.example.gestionb2c.service;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
import com.example.gestionb2c.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService implements IClientService{


    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClient(Long id) {
        try{
            return clientRepository.getById(id);
        }catch (RuntimeException exception){
            System.out.println(exception);
            return null;
        }

    }

    @Override
    public List<Client> getListClients() {

        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        return null;
    }

    @Override
    public Client getClientByEmail(String email) {

        return !email.isEmpty() ? clientRepository.findByEmail(email) : null;
    }

    @Override
    public List<Client> getListClientByGander(Gander gander) {

        return !gander.toString().isEmpty() ? clientRepository.findByGander(gander.toString()) : null ;
    }

    @Override
    public void deleteClient(Long id) {

    }

    @Override
    public Client updateClient(Client client) {
        return null;
    }
}
