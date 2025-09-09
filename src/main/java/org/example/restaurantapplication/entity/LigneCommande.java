package org.example.restaurantapplication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lignes_commande")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter

public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "plat_id")
    private Plat plat;
}
