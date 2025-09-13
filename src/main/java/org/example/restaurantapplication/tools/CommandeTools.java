package org.example.restaurantapplication.tools;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.CommandeDTO;
import org.example.restaurantapplication.dto.CommandeRequestDTO;
import org.example.restaurantapplication.entity.StatutCommande;
import org.example.restaurantapplication.service.CommandeService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandeTools {

    private final CommandeService commandeService;

    @Tool(
            name = "createCommande",
            description = """
  Crée une nouvelle commande pour un client existant à partir de son numéro de téléphone.
  Paramètres attendus (CommandeRequestDTO) :
  - telephone (String, requis) : Numéro de téléphone du client existant.
  Les champs 'dateCommande' et 'statut' sont gérés automatiquement.

  Exemple d'input JSON :
  {
    "telephone": "0601020304"
  }
  """
    )
    public CommandeDTO createCommande(CommandeRequestDTO commandeRequestDTO) {
        return commandeService.save(commandeRequestDTO);
    }

//    @Tool(
//            name = "updateCommandeStatus",
//            description = """
//                    Met à jour le statut d'une commande.
//                    Input: {
//                        commandeId: number,
//                        statut: string (valeurs possibles: EN_ATTENTE, EN_PREPARATION, PRETE, LIVREE)
//                    }
//                    Output: CommandeDTO avec statut mis à jour
//                    Exemple: updateCommandeStatus(1, "EN_PREPARATION")
//                    Erreur: Lance une exception si la commande n'existe pas
//                    """
//    )
    public CommandeDTO updateCommandeStatus(int commandeId, StatutCommande statut) {
        return commandeService.updateStatus(commandeId, statut);
    }

    @Tool(
            name = "getCommande",
            description = """
                    Récupère les détails d'une commande spécifique.
                    Input: id: number
                    Output: CommandeDTO {
                        id: number,
                        clientId: number,
                        telephone: string,
                        dateCommande: string,
                        statut: string,
                        lignesCommande: [...]
                    }
                    Exemple: getCommande(1)
                    Erreur: Lance une exception si la commande n'existe pas
                    """
    )
    public CommandeDTO getCommande(int id) {
        return commandeService.findById(id);
    }

    @Tool(
            name = "getCurrentCommandes",
            description = """
                    Récupère toutes les commandes actives (non livrées).
                    Input: Aucun paramètre
                    Output: Liste de CommandeDTO [
                        {
                            id: number,
                            clientId: number,
                            telephone: string,
                            dateCommande: string,
                            statut: string,
                            lignesCommande: [...]
                        },
                        ...
                    ]
                    Exemple: getCurrentCommandes()
                    """
    )
    public List<CommandeDTO> getCurrentCommandes() {
        return commandeService.findCurrentCommandes();
    }

//    @Tool(
//            name = "getClientCommandes",
//            description = """
//                    Récupère l'historique des commandes d'un client.
//                    Input: telephone: string (format: +212XXXXXXXXX)
//                    Output: Liste de CommandeDTO [
//                        {
//                            id: number,
//                            clientId: number,
//                            telephone: string,
//                            dateCommande: string,
//                            statut: string,
//                            lignesCommande: [...]
//                        },
//                        ...
//                    ]
//                    Exemple: getClientCommandes("+212661234567")
//                    Erreur: Lance une exception si le client n'existe pas
//                    """
//    )
    public List<CommandeDTO> getClientCommandes(String telephone) {
        return commandeService.findByClientTelephone(telephone);
    }
}
