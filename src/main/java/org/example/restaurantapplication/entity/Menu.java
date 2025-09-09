package org.example.restaurantapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menus")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"plats"})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Plat> plats;
}
