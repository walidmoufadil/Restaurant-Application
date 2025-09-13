package org.example.restaurantapplication.tools;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.PlatDTO;
import org.example.restaurantapplication.dto.PlatRequestDTO;
import org.example.restaurantapplication.service.PlatService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlatTools {

    private final PlatService platService;

//    @Tool(
//            name = "createPlat",
//            description = "Crée un nouveau plat. Paramètres: {nom: string, description: string, prix: number, disponibilite: boolean, menuId: number}. Retourne: PlatDTO avec ID généré"
//    )
//    public PlatDTO createPlat(PlatRequestDTO platRequestDTO) {
//        return platService.save(platRequestDTO);
//    }

//    @Tool(
//            name = "updatePlat",
//            description = "Met à jour un plat existant. Paramètres: {id: number, nom: string, description: string, prix: number, disponibilite: boolean, menuId: number}. Retourne: PlatDTO mis à jour"
//    )
//    public PlatDTO updatePlat(PlatDTO platDTO) {
//        return platService.update(platDTO);
//    }

    @Tool(
            name = "getPlat",
            description = """
                    Récupère les détails d'un plat spécifique.
                    Input: id: number
                    Output: PlatDTO {
                        id: number,
                        nom: string,
                        description: string,
                        prix: number,
                        disponibilite: boolean,
                        menuId: number
                    }
                    Exemple: getPlat(1)
                    Erreur: Lance une exception si le plat n'existe pas
                    """
    )
    public PlatDTO getPlat(int id) {
        return platService.findById(id);
    }

    @Tool(
            name = "getAllPlats",
            description = """
                    Récupère tous les plats disponibles.
                    Input: Aucun paramètre
                    Output: Liste de PlatDTO [
                        {
                            id: number,
                            nom: string,
                            description: string,
                            prix: number,
                            disponibilite: boolean,
                            menuId: number
                        },
                        ...
                    ]
                    Exemple: getAllPlats()
                    Note: Inclut tous les plats, qu'ils soient disponibles ou non
                    """
    )
    public List<PlatDTO> getAllPlats() {
        return platService.findAll();
    }
}
