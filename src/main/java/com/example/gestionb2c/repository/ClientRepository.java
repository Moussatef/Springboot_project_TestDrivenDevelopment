package com.example.gestionb2c.repository;

import com.example.gestionb2c.entity.Client;
import com.example.gestionb2c.enums.Gander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query(value = "SELECT * FROM Client WHERE Client.is_active = 'true' AND Client.email =?1", nativeQuery = true)
    Client getClientByEmailAndIsActive(String email);

    List<Client> findClientsByIsActiveAndGander(boolean active, Gander gander);
}
