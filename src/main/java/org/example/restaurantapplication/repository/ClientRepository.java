package org.example.restaurantapplication.repository;

import org.example.restaurantapplication.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String email);
    Client findByTelephone(String telephone);
}
