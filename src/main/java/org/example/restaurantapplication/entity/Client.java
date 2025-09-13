package org.example.restaurantapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
@ToString(exclude = {"commandes"})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;
}
