package org.example.restaurantapplication.tools;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.LigneCommandeDTO;
import org.example.restaurantapplication.dto.LigneCommandeRequestDTO;
import org.example.restaurantapplication.service.LigneCommandeService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LigneCommandeTools {

    private final LigneCommandeService ligneCommandeService;

    @Tool(
            name = "createLigneCommande",
            description = "Crée une nouvelle ligne de commande. Paramètres: {platId: number, quantite: number}. Retourne: LigneCommandeDTO {id: number, commandeId: number, platId: number, quantite: number} ou lance une exception si commande ou plat non trouvé"
    )
    public LigneCommandeDTO createLigneCommande(LigneCommandeRequestDTO requestDTO) {
        return ligneCommandeService.save(requestDTO);
    }

    @Tool(
            name = "updateLigneCommande",
            description = "Met à jour une ligne de commande. Paramètres: {id: number, commandeId: number, platId: number, quantite: number}. Retourne: LigneCommandeDTO mis à jour ou lance une exception si ligne non trouvée ou si la commande n'est plus modifiable"
    )
    public LigneCommandeDTO updateLigneCommande(LigneCommandeDTO ligneCommandeDTO) {
        return ligneCommandeService.update(ligneCommandeDTO);
    }

    @Tool(
            name = "getLigneCommande",
            description = "Récupère une ligne de commande par son ID. Paramètre: {id: number}. Retourne: LigneCommandeDTO {id, commandeId, platId, quantite} ou lance une exception si non trouvée"
    )
    public LigneCommandeDTO getLigneCommande(int id) {
        return ligneCommandeService.findById(id);
    }

    @Tool(
            name = "getAllLignesCommande",
            description = "Récupère la liste de toutes les lignes de commande. Ne prend aucun paramètre. Retourne List<LigneCommandeDTO>."
    )
    public List<LigneCommandeDTO> getAllLignesCommande() {
        return ligneCommandeService.findAll();
    }

//    @Tool(
//            name = "deleteLigneCommande",
//            description = "Supprime une ligne de commande. Paramètre: {id: number}. Ne retourne rien. Lance une exception si la ligne n'existe pas ou si la commande n'est plus en statut EN_ATTENTE"
//    )
    public void deleteLigneCommande(int id) {
        ligneCommandeService.deleteById(id);
    }

    @Tool(
            name = "getLignesCommandeByCommandeId",
            description = "Récupère toutes les lignes d'une commande. Paramètre: {commandeId: number}. Retourne: Liste de LigneCommandeDTO [{id, commandeId, platId, quantite}, ...] ou liste vide si aucune ligne trouvée"
    )
    public List<LigneCommandeDTO> getLignesCommandeByCommandeId(int commandeId) {
        return ligneCommandeService.findByCommandeId(commandeId);
    }
}
