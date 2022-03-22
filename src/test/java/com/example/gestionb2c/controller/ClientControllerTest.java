package com.example.gestionb2c.controller;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
import com.example.gestionb2c.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    Client clientOne ;
    Client clientTwo ;

    @BeforeEach
    void initClient(){
         clientOne = new Client(1L, "othman.moussatef@gmail.com","0637274172","Moussatef Othman", Gander.MALE,true,23);
         clientTwo = new Client(2L, "khadija@gmail.com","0735261781","Khadija yc",Gander.FEMALE,true,18);
    }



    @Test
    void addNewClient() {
    }

    @Test
    void getClients() throws Exception {

        List<Client> clientList = new ArrayList<>();
        clientList.add(clientOne);
        clientList.add(clientTwo);
        when(clientService.getListClients()).thenReturn(clientList);
        mockMvc.perform(get("/api/clients/all"))
                .andExpect(status().isOk());
    }

    @Test
    void updateClient() {
    }
}