package org.example.restaurantapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.CommandeDTO;
import org.example.restaurantapplication.dto.CommandeRequestDTO;
import org.example.restaurantapplication.entity.Client;
import org.example.restaurantapplication.entity.Commande;
import org.example.restaurantapplication.entity.StatutCommande;
import org.example.restaurantapplication.mapper.CommandeMapper;
import org.example.restaurantapplication.repository.ClientRepository;
import org.example.restaurantapplication.repository.CommandeRepository;
import org.example.restaurantapplication.service.CommandeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final CommandeMapper commandeMapper;

    @Override
    public CommandeDTO save(CommandeRequestDTO commandeRequestDTO) {
        // Vérifier si le client existe
        Client client = clientRepository.findByTelephone(commandeRequestDTO.getTelephone());
        if (client == null) {
            throw new RuntimeException("Client non trouvé avec le numéro: " + commandeRequestDTO.getTelephone());
        }

        Commande commande = commandeMapper.toEntity(commandeRequestDTO);
        commande.setClient(client);
        commande.setDateCommande(new Date());
        commande.setStatut(StatutCommande.EN_ATTENTE);

        return commandeMapper.toDTO(commandeRepository.save(commande));
    }

    @Override
    public CommandeDTO update(CommandeDTO commandeDTO) {
        Commande existingCommande = commandeRepository.findById(commandeDTO.getId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + commandeDTO.getId()));

        Commande commande = commandeMapper.toEntity(commandeDTO);
        commande.setClient(existingCommande.getClient());
        commande.setDateCommande(existingCommande.getDateCommande());

        return commandeMapper.toDTO(commandeRepository.save(commande));
    }

    @Override
    public CommandeDTO updateStatus(int commandeId, StatutCommande statut) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + commandeId));

        commande.setStatut(statut);
        return commandeMapper.toDTO(commandeRepository.save(commande));
    }

    @Override
    public CommandeDTO findById(int id) {
        return commandeRepository.findById(id)
                .map(commandeMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + id));
    }

    @Override
    public List<CommandeDTO> findAll() {
        return commandeRepository.findAll().stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + id));

        if (commande.getStatut() != StatutCommande.EN_COURS) {
            throw new RuntimeException("Impossible de supprimer une commande qui n'est plus en attente");
        }

        commandeRepository.deleteById(id);
    }

    @Override
    public List<CommandeDTO> findByClientId(Integer clientId) {
        return commandeRepository.findByClientId(clientId).stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDTO> findByStatut(StatutCommande statut) {
        return commandeRepository.findByStatut(statut).stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDTO> findByClientTelephone(String telephone) {
        Client client = clientRepository.findByTelephone(telephone);
        if (client == null) {
            throw new RuntimeException("Client non trouvé avec le numéro: " + telephone);
        }
        return commandeRepository.findByClientId(client.getId()).stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDTO> findCurrentCommandes() {
        return commandeRepository.findByStatutNotIn(List.of(StatutCommande.LIVREE, StatutCommande.ANNULEE))
                .stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
