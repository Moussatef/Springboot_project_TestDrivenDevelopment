package com.example.gestionb2c.service;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
import com.example.gestionb2c.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    Client clientOne ;
    Client clientTwo ;
    List<Client> clientList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clientOne = new Client(1L, "othman.moussatef@gmail.com","0637274172","Moussatef Othman", Gander.MALE,true,23);
        clientTwo = new Client(2L, "khadija@gmail.com","0735261781","Khadija hd",Gander.MALE,true,18);
        clientList.add(clientOne);
        clientList.add(clientTwo);
    }

    @Test
    void getClient() {
    }

    @Test
    void getListClients() {
        given(clientRepository.findAll()).willReturn(clientList);
        List<Client> listClients = clientService.getListClients();
        assertEquals(listClients, clientList);
        verify(clientRepository).findAll();
    }

    @Test
    void saveClients() {

        Mockito.lenient().when(clientRepository.saveAll(clientList)).thenReturn(clientList);
        List<Client> clients = clientService.saveClients(clientList);
        assertThat(clients).isEqualTo(clientList);
    }

    @Test
    void getClientByEmail() {
        given(clientRepository.getClientByEmailAndIsActive(clientOne.getEmail())).willReturn(clientOne);
        Client clientFind = clientService.getClientByEmail(clientOne.getEmail());
        assertEquals(clientFind, clientOne);
        verify(clientRepository).getClientByEmailAndIsActive(clientOne.getEmail());
    }

    @Test
    void getListClientByGander() {
        given(clientRepository.findClientsByIsActiveAndGander(true,Gander.MALE)).willReturn(clientList);
        List<Client> clientsFind = clientService.getListClientByGander(Gander.MALE);

        assertEquals(clientsFind, clientList);
        verify(clientRepository).findClientsByIsActiveAndGander(true,Gander.MALE);
    }

    @Test
    void deleteClient() {
    }

    @Test
    void updateClient() {
    }
}