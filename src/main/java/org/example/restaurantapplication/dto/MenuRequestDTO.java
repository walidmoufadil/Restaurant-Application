package org.example.restaurantapplication.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequestDTO {
    private String nom;
    private Date dateCreation;
}
