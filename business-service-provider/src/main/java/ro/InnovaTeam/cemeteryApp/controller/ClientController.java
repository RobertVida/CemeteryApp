package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.ClientList;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.model.Client;
import ro.InnovaTeam.cemeteryApp.service.ClientService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.ClientUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.ClientUtil.toDTO;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by Roxana on 11/28/2014.
 */
@Controller
public class ClientController {

    public static final String CLIENT_URL = "/client";
    public static final String CLIENTS_URL = "/clients";
    public static final String SPECIFIC_CLIENT_URL = CLIENT_URL + "/{clientId}";
    public static final String SPECIFIC_USER_CLIENT_URL = CLIENT_URL + "/{userId}/{clientId}";

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = CLIENT_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.create(toDB(clientDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_CLIENT_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientDTO delete(@PathVariable Integer userId, @PathVariable Integer clientId) {
        return toDTO(clientService.delete(userId, clientId));
    }

    @RequestMapping(value = SPECIFIC_CLIENT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientDTO update(@PathVariable Integer clientId, @RequestBody @Valid ClientDTO clientDTO) {
        Client client = toDB(clientDTO);
        client.setId(clientId);
        return toDTO(clientService.update(client));
    }

    @RequestMapping(value = SPECIFIC_CLIENT_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientDTO findById(@PathVariable Integer clientId) {
        return toDTO(clientService.findById(clientId));
    }

    @RequestMapping(value = CLIENTS_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientList findByFilter(@RequestBody FilterDTO filterDTO) {
        return new ClientList(toDTO(clientService.findByFilter(toDB(filterDTO))));
    }
}
