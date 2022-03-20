package com.example.gestionb2c.service;

import com.example.gestionb2c.entity.Client;

import java.util.List;

public interface IClientService {
    Client getClient(Long id);
    List<Client> getListClients();
    Client saveClient(Client client);
}
