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
            if(clientRepository.findById(id).isPresent())
                return clientRepository.findById(id).get();
            return null;
        }catch (IllegalStateException exception){
            System.out.println(exception.getMessage());
            return null;
        }

    }

    @Override
    public List<Client> getListClients() {

        return clientRepository.findAll();
    }

    @Override
    public List<Client> saveClients(List<Client> client) {
        try {
            return clientRepository.saveAll(client);
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Client getClientByEmail(String email) {

        return !email.isEmpty() ? clientRepository.findByEmailAndActiveIsTrue(email) : null;
    }

    @Override
    public List<Client> getListClientByGander(Gander gander) {
        return !gander.toString().isEmpty() ? clientRepository.findByGander(gander) : null ;
    }

    @Override
    public void deleteClient(Long id) {
        if(clientRepository.findById(id).isPresent()){
            Client client = clientRepository.findById(id).get();
            client.setActive(false);
            clientRepository.save(client);
        }else{
            throw new IllegalStateException("User ID : "+id+" is not exists");
        }
    }

    @Override
    public Client updateClient(Client client) {
        Client clientUpdating = clientRepository.findById(client.getId()).orElse(null);
        if(clientUpdating != null){
            clientUpdating.setFullName(client.getFullName());
            clientUpdating.setEmail((client.getEmail()));
            clientUpdating.setGander(client.getGander());
            clientUpdating.setOld(client.getOld());
            clientUpdating.setPhone(client.getPhone());
            return clientRepository.save(clientUpdating);
        }
        return null;
    }
}
