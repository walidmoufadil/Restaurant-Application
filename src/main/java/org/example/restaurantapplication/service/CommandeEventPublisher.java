package org.example.restaurantapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.CommandeStatusEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandeEventPublisher {
    private static final String TOPIC = "commande-status-changes";
    private final KafkaTemplate<String, CommandeStatusEventDTO> kafkaTemplate;

    public void publierChangementStatus(CommandeStatusEventDTO event) {
        kafkaTemplate.send(TOPIC, String.valueOf(event.getCommandeId()), event);
    }
}
