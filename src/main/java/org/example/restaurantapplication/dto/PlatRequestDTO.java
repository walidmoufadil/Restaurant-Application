package org.example.restaurantapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatRequestDTO {
    private String nom;
    private String description;
    private double prix;
    private boolean disponibilite;
    private int menuId;
}
