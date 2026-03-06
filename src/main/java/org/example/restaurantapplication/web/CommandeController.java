package org.example.restaurantapplication.web;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.CommandeDTO;
import org.example.restaurantapplication.dto.LigneCommandeDTO;
import org.example.restaurantapplication.dto.PlatDTO;
import org.example.restaurantapplication.entity.StatutCommande;
import org.example.restaurantapplication.service.CommandeService;
import org.example.restaurantapplication.service.LigneCommandeService;
import org.example.restaurantapplication.service.PlatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;
    private final LigneCommandeService ligneCommandeService;
    private final PlatService platService;

    @GetMapping
    public ResponseEntity<List<CommandeDTO>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.findAll());
    }

    @GetMapping("/{id}")
    //@RolesAllowed(value = "ADMIN")
    public ResponseEntity<Map<String, Object>> getCommandeDetails(@PathVariable Integer id) {
        CommandeDTO commande = commandeService.findById(id);
        List<LigneCommandeDTO> lignesCommande = ligneCommandeService.findByCommandeId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("commande", commande);
        response.put("lignesCommande", lignesCommande);

        return ResponseEntity.ok(response);
    }

    @RolesAllowed(value = "ADMIN")
    @GetMapping("/client/{telephone}")
    public ResponseEntity<List<CommandeDTO>> getCommandesByClientTelephone(@PathVariable String telephone) {
        return ResponseEntity.ok(commandeService.findByClientTelephone(telephone));
    }

    //@RolesAllowed(value = "ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<CommandeDTO>> getAllCommandesWithDetails() {
        List<CommandeDTO> commandes = commandeService.findAll();
        for (CommandeDTO commande : commandes) {
            List<LigneCommandeDTO> lignesCommande = ligneCommandeService.findByCommandeId(commande.getId());
            commande.setLignesCommande(lignesCommande);
        }
        return ResponseEntity.ok(commandes);
    }

    //@RolesAllowed(value = "ADMIN")
    @GetMapping("/encours")
    public ResponseEntity<List<CommandeDTO>> getCommandesEnCours() {
        return ResponseEntity.ok(commandeService.findByStatut(StatutCommande.EN_COURS));
    }

    //@RolesAllowed(value = "ADMIN")
    @GetMapping("/plat/{id}")
    public ResponseEntity<PlatDTO> getPlatById(@PathVariable int id) {
        PlatDTO plat = platService.findById(id);
        return ResponseEntity.ok(plat);
    }

    @PostMapping("/updateStatus/{id}")
    public void updateCommandeStatus(@PathVariable Integer id, @RequestParam StatutCommande statut) {
        commandeService.updateStatus(id, statut);
    }
}
