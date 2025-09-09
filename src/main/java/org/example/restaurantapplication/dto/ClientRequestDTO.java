package org.example.restaurantapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequestDTO {
    private String nom;
    private String email;
    private String telephone;
}
