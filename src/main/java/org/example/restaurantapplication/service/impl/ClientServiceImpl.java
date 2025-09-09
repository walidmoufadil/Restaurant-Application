package org.example.restaurantapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.ClientDTO;
import org.example.restaurantapplication.dto.ClientRequestDTO;
import org.example.restaurantapplication.entity.Client;
import org.example.restaurantapplication.mapper.ClientMapper;
import org.example.restaurantapplication.repository.ClientRepository;
import org.example.restaurantapplication.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDTO save(ClientRequestDTO clientRequestDTO) {
        Client client = ClientMapper.toEntity(clientRequestDTO);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.toDTO(savedClient);
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) {
        Client client = ClientMapper.toEntity(clientDTO);
        Client updatedClient = clientRepository.save(client);
        return ClientMapper.toDTO(updatedClient);
    }

    @Override
    public ClientDTO findById(int id) {
        return clientRepository.findById(id)
                .map(ClientMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + id));
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO findByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return ClientMapper.toDTO(client);
    }
}
