package com.example.gestionb2c.service;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;

import java.util.List;

public interface IClientService {
    Client getClient(Long id);
    List<Client> getListClients();
    Client saveClient(Client client);
    Client getClientByEmail(String email);
    List<Client> getListClientByGander(Gander gander);
    void deleteClient(Long id);
    Client updateClient(Client client);
}
