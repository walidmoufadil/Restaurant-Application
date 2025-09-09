package org.example.restaurantapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "plats")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"lignesCommande"})
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    private String description;

    @Column(nullable = false)
    private double prix;

    private boolean disponibilite = true;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "plat", cascade = CascadeType.ALL)
    private List<LigneCommande> lignesCommande;
}
