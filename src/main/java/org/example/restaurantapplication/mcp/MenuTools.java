package org.example.restaurantapplication.tools;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.MenuDTO;
import org.example.restaurantapplication.dto.MenuRequestDTO;
import org.example.restaurantapplication.service.MenuService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuTools {

    private final MenuService menuService;

//    @Tool(
//            name = "createMenu",
//            description = "Crée un nouveau menu. Paramètres: {nom: string, dateCreation: date}. Retourne: MenuDTO {id: number, nom: string, dateCreation: date} ou lance une exception si le nom existe déjà"
//    )
//    public MenuDTO createMenu(MenuRequestDTO menuRequestDTO) {
//        return menuService.save(menuRequestDTO);
//    }

//    @Tool(
//            name = "updateMenu",
//            description = "Met à jour un menu existant. Paramètres: {id: number, nom: string, dateCreation: date}. Retourne: MenuDTO mis à jour ou lance une exception si menu non trouvé"
//    )
//    public MenuDTO updateMenu(MenuDTO menuDTO) {
//        return menuService.update(menuDTO);
//    }

    @Tool(
            name = "getMenu",
            description = """
                    Récupère les détails d'un menu spécifique.
                    Input: id: number
                    Output: MenuDTO {
                        id: number,
                        nom: string,
                        dateCreation: string (format: yyyy-MM-dd)
                    }
                    Exemple: getMenu(1)
                    Erreur: Lance une exception si le menu n'existe pas
                    """
    )
    public MenuDTO getMenu(int id) {
        return menuService.findById(id);
    }

    @Tool(
            name = "getAllMenus",
            description = """
                    Récupère tous les menus disponibles.
                    Input: Aucun paramètre
                    Output: Liste de MenuDTO [
                        {
                            id: number,
                            nom: string,
                            dateCreation: string (format: yyyy-MM-dd)
                        },
                        ...
                    ]
                    Exemple: getAllMenus()
                    Note: Retourne une liste vide si aucun menu n'existe
                    """
    )
    public List<MenuDTO> getAllMenus() {
        return menuService.findAll();
    }

//    @Tool(
//            name = "getCurrentMenu",
//            description = "Récupère le menu actuel (le plus récent). Pas de paramètres. Retourne: MenuDTO {id: number, nom: string, dateCreation: date} ou lance une exception si aucun menu n'existe"
//    )
//    public MenuDTO getCurrentMenu() {
//        return menuService.findCurrentMenu();
//    }
}
