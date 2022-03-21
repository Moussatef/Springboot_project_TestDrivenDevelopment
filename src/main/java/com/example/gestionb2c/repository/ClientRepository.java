package com.example.gestionb2c.repository;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByEmailAndActiveIsTrue(String email);
    List<Client> findByGander(Gander gander);
}
