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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plat_id")
    private Plat plat;
}
