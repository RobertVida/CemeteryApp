package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.ClientEAO;
import ro.InnovaTeam.cemeteryApp.model.Client;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.service.ClientService;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.List;

/**
 * Created by Roxana on 11/28/2014.
 */
@Transactional
@Service
public class ClientServiceImpl extends LoggableService<Client, ClientEAO, LogEntryService> implements ClientService {

    @Autowired
    private ClientEAO clientEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Client client) {
        return loggedCreate(clientEAO, logService, client);
    }

    @Override
    public Client delete(Integer userId, Integer id) {
        //TODO cascade delete
        return loggedDelete(clientEAO, logService, userId, id);
    }

    @Override
    public Client update(Client client) {
        return loggedUpdate(clientEAO, logService, client);
    }

    @Override
    public Client findById(Integer id) {
        return clientEAO.findById(id);
    }

    @Override
    public List<Client> findByFilter(Filter filter) {
        return clientEAO.findByFilter(filter);
    }
}
