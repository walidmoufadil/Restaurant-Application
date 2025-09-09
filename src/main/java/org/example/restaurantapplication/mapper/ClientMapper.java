package org.example.restaurantapplication.mapper;

import org.example.restaurantapplication.dto.ClientDTO;
import org.example.restaurantapplication.dto.ClientRequestDTO;
import org.example.restaurantapplication.entity.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) return null;

        return ClientDTO.builder()
                .id(client.getId())
                .nom(client.getNom())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .build();
    }

    public static Client toEntity(ClientRequestDTO dto) {
        if (dto == null) return null;

        return Client.builder()
                .nom(dto.getNom())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .build();
    }

    public static Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        return Client.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .build();
    }
}
