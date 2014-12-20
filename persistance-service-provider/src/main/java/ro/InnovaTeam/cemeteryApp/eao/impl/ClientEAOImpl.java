package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.ClientEAO;
import ro.InnovaTeam.cemeteryApp.helpers.FilteredQueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Client;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

/**
 * Created by Roxana on 11/28/2014.
 */
@Component
public class ClientEAOImpl extends EntityEAOImpl<Client> implements ClientEAO {

    private static final String TABLE = "clients";

    @Override
    public Integer create(Client client) {
        Integer id = create(TABLE, client);
        flush();
        return id;
    }

    @Override
    public Client delete(Integer id) {
        Client client = delete(TABLE, id);
        flush();
        return client;
    }

    @Override
    public Client update(Client client) {
        update(TABLE, client);
        flush();
        return findById(client.getId());
    }

    @Override
    public Client findById(Integer id) {
        Client client = findById(TABLE, id);
        flush();
        return client;
    }

    @Override
    public List<Client> findByFilter(Filter filter) {
        return findByFilter(TABLE, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> findByFilter(String tableName, Filter filter) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setCriteriaSearchableColumns("first_name", "last_name", "cnp", "phone_number", "home_address")
                .build(getSession()).list();
    }
}
