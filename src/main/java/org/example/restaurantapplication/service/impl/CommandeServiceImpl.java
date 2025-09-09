package org.example.restaurantapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.CommandeDTO;
import org.example.restaurantapplication.dto.CommandeRequestDTO;
import org.example.restaurantapplication.entity.Commande;
import org.example.restaurantapplication.entity.Client;
import org.example.restaurantapplication.entity.StatutCommande;
import org.example.restaurantapplication.mapper.CommandeMapper;
import org.example.restaurantapplication.repository.CommandeRepository;
import org.example.restaurantapplication.repository.ClientRepository;
import org.example.restaurantapplication.service.CommandeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;

    @Override
    public CommandeDTO save(CommandeRequestDTO commandeRequestDTO) {
        Client client = clientRepository.findById(commandeRequestDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        Commande commande = CommandeMapper.toEntity(commandeRequestDTO, client);
        Commande savedCommande = commandeRepository.save(commande);
        return CommandeMapper.toDTO(savedCommande);
    }

    @Override
    public CommandeDTO update(CommandeDTO commandeDTO) {
        Client client = clientRepository.findById(commandeDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        Commande commande = CommandeMapper.toEntity(commandeDTO, client);
        Commande updatedCommande = commandeRepository.save(commande);
        return CommandeMapper.toDTO(updatedCommande);
    }

    @Override
    public CommandeDTO findById(int id) {
        return commandeRepository.findById(id)
                .map(CommandeMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'id: " + id));
    }

    @Override
    public List<CommandeDTO> findAll() {
        return commandeRepository.findAll().stream()
                .map(CommandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public List<CommandeDTO> findByClientId(Integer clientId) {
        return commandeRepository.findByClientId(clientId).stream()
                .map(CommandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDTO> findByStatut(StatutCommande statut) {
        return commandeRepository.findByStatut(statut).stream()
                .map(CommandeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
