package org.example.restaurantapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommandeDTO {
    private int id;
    private int quantite;
    private int commandeId;
    private int platId;
}
