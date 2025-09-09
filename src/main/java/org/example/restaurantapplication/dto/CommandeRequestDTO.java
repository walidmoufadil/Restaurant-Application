package org.example.restaurantapplication.dto;

import lombok.*;
import org.example.restaurantapplication.entity.StatutCommande;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeRequestDTO {
    private Date dateCommande;
    private StatutCommande statut;
    private int clientId;
}
