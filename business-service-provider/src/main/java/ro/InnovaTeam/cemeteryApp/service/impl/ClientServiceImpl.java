package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.ClientEAO;
import ro.InnovaTeam.cemeteryApp.model.Client;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.service.ClientService;

import java.util.List;

/**
 * Created by Roxana on 11/28/2014.
 */
@Transactional
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientEAO clientEAO;

    @Override
    public Integer create(Client client) {
        return clientEAO.create(client);
    }

    @Override
    public Client delete(Integer id) {
        //TODO cascade delete
        return clientEAO.delete(id);
    }

    @Override
    public Client update(Client client) {
        return clientEAO.update(client);
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
