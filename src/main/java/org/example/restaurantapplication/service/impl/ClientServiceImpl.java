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
        // Vérifier d'abord si le client existe déjà avec ce numéro de téléphone
        Client existingClient = clientRepository.findByTelephone(clientRequestDTO.getTelephone());
        if (existingClient != null) {
            return ClientMapper.toDTO(existingClient);
        }

        // Si le client n'existe pas, créer un nouveau
        Client client = ClientMapper.toEntity(clientRequestDTO);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.toDTO(savedClient);
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) {
        // Vérifier si le client existe
        Client existingClient = clientRepository.findByTelephone(clientDTO.getTelephone());
        if (existingClient == null) {
            throw new RuntimeException("Client non trouvé avec le numéro: " + clientDTO.getTelephone());
        }

        Client client = ClientMapper.toEntity(clientDTO);
        client.setId(existingClient.getId()); // Assurer que l'ID est préservé
        Client updatedClient = clientRepository.save(client);
        return ClientMapper.toDTO(updatedClient);
    }

    @Override
    public ClientDTO findByTelephone(String telephone) {
        Client client = clientRepository.findByTelephone(telephone);
        return client != null ? ClientMapper.toDTO(client) : null;
    }

    @Override
    public ClientDTO findById(int id) {
        return clientRepository.findById(id)
                .map(ClientMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + id));
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
}
