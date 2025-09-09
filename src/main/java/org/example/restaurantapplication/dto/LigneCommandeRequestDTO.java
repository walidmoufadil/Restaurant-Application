package org.example.restaurantapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommandeRequestDTO {
    private int quantite;
    private int commandeId;
    private int platId;
}
