package org.example.restaurantapplication;

import org.example.restaurantapplication.dto.*;
import org.example.restaurantapplication.entity.StatutCommande;
import org.example.restaurantapplication.service.*;
import org.example.restaurantapplication.tools.*;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    @Bean
    MethodToolCallbackProvider getmethodToolCallbackProvider(ClientTools clientTools,
                                                           MenuTools menuTools,
                                                           PlatTools platTools,
                                                           CommandeTools commandeTools,
                                                           LigneCommandeTools ligneCommandeTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(clientTools, menuTools, platTools, commandeTools, ligneCommandeTools)
                .build();
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ClientService clientService,
            MenuService menuService,
            PlatService platService,
            CommandeService commandeService,
            LigneCommandeService ligneCommandeService
    ) {
        return args -> {
            // Création des menus
            MenuRequestDTO menuPrincipal = MenuRequestDTO.builder()
                    .nom("Menu Principal")
                    .dateCreation(new Date())
                    .build();
            MenuDTO savedMenuPrincipal = menuService.save(menuPrincipal);

            MenuRequestDTO menuSpecialites = MenuRequestDTO.builder()
                    .nom("Spécialités Marocaines")
                    .dateCreation(new Date())
                    .build();
            MenuDTO savedMenuSpecialites = menuService.save(menuSpecialites);

            // Création des plats du menu principal
            PlatRequestDTO[] platsMenu = {
                    PlatRequestDTO.builder()
                            .nom("Couscous Royal")
                            .description("Couscous traditionnel avec agneau, poulet, merguez, légumes de saison")
                            .prix(18.99)
                            .disponibilite(true)
                            .menuId(savedMenuPrincipal.getId())
                            .build(),
                    PlatRequestDTO.builder()
                            .nom("Tajine de Poulet aux Citrons")
                            .description("Tajine de poulet aux citrons confits et olives")
                            .prix(16.99)
                            .disponibilite(true)
                            .menuId(savedMenuPrincipal.getId())
                            .build(),
                    PlatRequestDTO.builder()
                            .nom("Tajine d'Agneau aux Pruneaux")
                            .description("Tajine d'agneau aux pruneaux, amandes et œufs")
                            .prix(19.99)
                            .disponibilite(true)
                            .menuId(savedMenuPrincipal.getId())
                            .build(),
                    PlatRequestDTO.builder()
                            .nom("Pastilla au Poulet")
                            .description("Pastilla traditionnelle au poulet et amandes")
                            .prix(15.99)
                            .disponibilite(true)
                            .menuId(savedMenuPrincipal.getId())
                            .build()
            };

            // Création des plats des spécialités
            PlatRequestDTO[] platsSpecialites = {
                    PlatRequestDTO.builder()
                            .nom("Rfissa")
                            .description("Msemen émietté avec poulet, lentilles et fenugrec")
                            .prix(17.99)
                            .disponibilite(true)
                            .menuId(savedMenuSpecialites.getId())
                            .build(),
                    PlatRequestDTO.builder()
                            .nom("Tanjia Marrakchia")
                            .description("Spécialité marrakchie de viande confite")
                            .prix(21.99)
                            .disponibilite(true)
                            .menuId(savedMenuSpecialites.getId())
                            .build(),
                    PlatRequestDTO.builder()
                            .nom("Poisson à la Charmoula")
                            .description("Poisson frais mariné aux épices et herbes")
                            .prix(20.99)
                            .disponibilite(true)
                            .menuId(savedMenuSpecialites.getId())
                            .build(),
                    PlatRequestDTO.builder()
                            .nom("M'hammer")
                            .description("Agneau confit aux épices")
                            .prix(23.99)
                            .disponibilite(true)
                            .menuId(savedMenuSpecialites.getId())
                            .build()
            };

            // Sauvegarde des plats
            for (PlatRequestDTO plat : platsMenu) {
                platService.save(plat);
            }
            for (PlatRequestDTO plat : platsSpecialites) {
                platService.save(plat);
            }

            // Création d'un client test
            ClientRequestDTO clientRequest = ClientRequestDTO.builder()
                    .nom("Jean Dupont")
                    .email("jean.dupont@email.com")
                    .telephone("0612345678")
                    .build();
            ClientDTO savedClient = clientService.save(clientRequest);


            // Affichage des menus et plats disponibles
            System.out.println("\n=== Menus disponibles ===");
            menuService.findAll().forEach(menu -> {
                System.out.println("\nMenu: " + menu.getNom());
                List<PlatDTO> plats = platService.findByMenuId(menu.getId());
                plats.forEach(plat -> {
                    System.out.println("- " + plat.getNom() + " (" + plat.getPrix() + "€)");
                    System.out.println("  " + plat.getDescription());
                });
            });

            // Création d'un client
            ClientRequestDTO clientRequest2 = ClientRequestDTO.builder()
                    .nom("Mohammed")
                    .telephone("0600000000")
                    .build();
            ClientDTO client = clientService.save(clientRequest2);
            System.out.println("Client créé: " + client);

            // Création d'une commande
            CommandeRequestDTO commandeRequest = CommandeRequestDTO.builder()
                    .telephone(client.getTelephone())
                    .build();
            CommandeDTO commande = commandeService.save(commandeRequest);
            System.out.println("Commande créée: " + commande);

            // Création d'une ligne de commande
            // On prend le premier plat du menu pour l'exemple
            List<PlatDTO> plats = platService.findAll();
            if (!plats.isEmpty()) {
                LigneCommandeRequestDTO ligneCommandeRequest = LigneCommandeRequestDTO.builder()
                        .commandeId(commande.getId())
                        .platId(plats.get(0).getId())
                        .quantite(2)
                        .build();
                LigneCommandeDTO ligneCommande = ligneCommandeService.save(ligneCommandeRequest);
                System.out.println("Ligne de commande créée: " + ligneCommande);
            }
        };
    }
}
