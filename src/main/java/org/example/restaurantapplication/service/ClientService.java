package org.example.restaurantapplication.service;

import org.example.restaurantapplication.dto.ClientDTO;
import org.example.restaurantapplication.dto.ClientRequestDTO;
import java.util.List;

public interface ClientService {
    ClientDTO save(ClientRequestDTO clientRequestDTO);
    ClientDTO update(ClientDTO clientDTO);
    ClientDTO findById(int id);
    List<ClientDTO> findAll();
    void deleteById(int id);
    ClientDTO findByEmail(String email);
}
