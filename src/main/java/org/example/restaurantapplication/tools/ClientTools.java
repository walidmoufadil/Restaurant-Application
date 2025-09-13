package org.example.restaurantapplication.tools;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.ClientDTO;
import org.example.restaurantapplication.dto.ClientRequestDTO;
import org.example.restaurantapplication.service.ClientService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientTools {

    private final ClientService clientService;

    @Tool(
            name = "createClient",
            description = """
                    Crée un nouveau client dans le système.
                    Input: ClientRequestDTO {
                        nom: string (obligatoire),
                        telephone: string 
                    }
                    Output: ClientDTO {
                        id: number,
                        nom: string,
                        telephone: string
                    }
                    Exemple: createClient({"nom": "Mohamed Alami", "telephone": "+212661234567"})
                    Erreur: Lance une exception si le numéro de téléphone existe déjà
                    """
    )
    public ClientDTO createClient(ClientRequestDTO clientRequestDTO) {
        return clientService.save(clientRequestDTO);
    }

//    @Tool(
//            name = "updateClient",
//            description = "Met à jour les informations d'un client. Paramètres: {id: number, nom: string, telephone: string}. Retourne: ClientDTO {id: number, nom: string, telephone: string} ou lance une exception si client non trouvé"
//    )
//    public ClientDTO updateClient(ClientDTO clientDTO) {
//        return clientService.update(clientDTO);
//    }

    @Tool(
            name = "findClientByPhone",
            description = """
                    Recherche un client par son numéro de téléphone.
                    Input: string 
                    Output: ClientDTO {
                        id: number,
                        nom: string,
                        telephone: string
                    } ou null si client non trouvé
                    Exemple: findClientByPhone("0661234567")
                    """
    )
    public ClientDTO findClientByPhone(String telephone) {
        return clientService.findByTelephone(telephone);
    }

    @Tool(
            name = "getAllClients",
            description = """
                    Récupère la liste de tous les clients enregistrés.
                    Input: Aucun paramètre
                    Output: Liste de ClientDTO [
                        {
                            id: number,
                            nom: string,
                            telephone: string
                        },
                        ...
                    ]
                    Exemple: getAllClients()
                    """
    )
    public List<ClientDTO> getAllClients() {
        return clientService.findAll();
    }
}
