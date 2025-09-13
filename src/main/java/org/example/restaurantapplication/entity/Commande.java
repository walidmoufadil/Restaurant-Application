package org.example.restaurantapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commandes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"id", "dateCommande", "statut"})
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;

    @Enumerated(EnumType.STRING)
    private StatutCommande statut;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneCommande> lignesCommande;
}
