package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roxana on 11/28/2014.
 */
public abstract class ClientUtil {

    public static Client toDB(ClientDTO clientDTO){
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setCnp(clientDTO.getCnp());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setAddress(clientDTO.getAddress());
        client.setUserId(clientDTO.getUserId());

        return client;
    }

    public static ClientDTO toDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setCnp(client.getCnp());
        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setAddress(client.getAddress());

        return clientDTO;
    }

    public static List<Client> toDB(List<ClientDTO> clientDTOs){
        List<Client> client = new ArrayList<Client>();
        for(ClientDTO clientDTO : clientDTOs){
            client.add(toDB(clientDTO));
        }
        return client;
    }

    public static List<ClientDTO> toDTO(List<Client> client){
        List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
        for(Client c : client){
            clientDTOs.add(toDTO(c));
        }
        return clientDTOs;
    }
}
