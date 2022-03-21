package com.example.gestionb2c.service;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
import com.example.gestionb2c.repository.ClientRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class ClientService implements IClientService{


    private Logger log ;
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
            String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
            String regexPhone = "^([0]{1}[5-7]{1})?[0-9]{8}$";

            Pattern patternEmail = Pattern.compile(regexEmail);
            Pattern patternPhone = Pattern.compile(regexPhone);

            for(Client element : client){
                Matcher matcherEmail = patternEmail.matcher(element.getEmail());
                Matcher matcherPhone = patternPhone.matcher(element.getPhone());
                if(!matcherEmail.matches() || !matcherPhone.matches() ){
                   // throw new  IllegalStateException("Email is not valide :"+element.getEmail());
                    log.error("Email is not valid :  ");
                    return null;
                }
                //System.out.println(email +" : "+ matcher.matches());
            }
            return clientRepository.saveAll(client);
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Client getClientByEmail(String email) {

        return !email.isEmpty() ? clientRepository.getClientByEmailAndIsActive(email) : null;

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
