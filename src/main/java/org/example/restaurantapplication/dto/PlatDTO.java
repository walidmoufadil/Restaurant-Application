package org.example.restaurantapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatDTO {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private boolean disponibilite;
    private int menuId;
}
