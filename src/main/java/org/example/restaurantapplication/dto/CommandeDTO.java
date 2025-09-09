package org.example.restaurantapplication.dto;

import lombok.*;
import org.example.restaurantapplication.entity.StatutCommande;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDTO {
    private int id;
    private Date dateCommande;
    private StatutCommande statut;
    private int clientId;
}
