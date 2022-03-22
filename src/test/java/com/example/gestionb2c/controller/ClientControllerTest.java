package com.example.gestionb2c.controller;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
import com.example.gestionb2c.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
    void addNewClient() throws Exception {
        List<Client> clientList = List.of(clientOne,clientTwo);
        when(clientService.saveClients(clientList)).thenReturn(clientList);

        mockMvc.perform(post("/api/clients/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clientList)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

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
    void updateClient()  throws Exception {

            when(clientService.updateClient(clientOne))
                    .thenReturn(clientOne);
            mockMvc.perform(put("/api/clients/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(clientOne)))
                    .andExpect(status().isOk());
    }

    @Test
    void getClient() throws Exception {
        Mockito.lenient().when(clientService.getClient(clientOne.getId())).thenReturn(clientOne);
        mockMvc.perform(get("/api/clients/"+clientOne.getId())).andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getClientByEmail() throws Exception {
        Mockito.lenient().when(clientService.getClientByEmail(clientOne.getEmail())).thenReturn(clientOne);
        mockMvc.perform(get("/api/clients/email/"+clientOne.getEmail())).andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    void getClientByGander() throws Exception {
        List<Client> clientList =  List.of(clientOne);
        Mockito.lenient().when(clientService.getListClientByGander(Gander.MALE)).thenReturn(clientList);
        mockMvc.perform(get("/api/clients/gander/"+clientOne.getGander())).andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }
}