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
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.ClientService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.ClientUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.ClientUtil.toDTO;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by Roxana on 11/28/2014.
 */
@Controller
public class ClientController extends ExceptionHandledController {

    public static final String CLIENT_URL = "/client";
    public static final String CLIENTS_URL = "/clients";
    public static final String SPECIFIC_CLIENT_URL = CLIENT_URL + "/{clientId}";

    @Autowired
    private ClientService clientService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = CLIENT_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid ClientDTO clientDTO) {
        clientDTO.setUserId(getUserId(token));
        return clientService.create(toDB(clientDTO));
    }

    @RequestMapping(value = SPECIFIC_CLIENT_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientDTO delete(@RequestHeader("Authorization-Token") String token, @PathVariable Integer clientId) {
        return toDTO(clientService.delete(getUserId(token), clientId));
    }

    @RequestMapping(value = SPECIFIC_CLIENT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientDTO update(@RequestHeader("Authorization-Token") String token, @PathVariable Integer clientId, @RequestBody @Valid ClientDTO clientDTO) {
        Client client = toDB(clientDTO);
        client.setUserId(getUserId(token));
        client.setId(clientId);
        return toDTO(clientService.update(client));
    }

    @RequestMapping(value = SPECIFIC_CLIENT_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientDTO findById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer clientId) {
        isLoggedIn(token);
        return toDTO(clientService.findById(clientId));
    }

    @RequestMapping(value = CLIENTS_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientList findByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new ClientList(toDTO(clientService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = CLIENTS_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return clientService.countByFilter(toDB(filterDTO));
    }

    private Integer getUserId(String token) {
        return authService.getAdminAccess(token).getId();
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
