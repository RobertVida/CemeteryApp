package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.CemeteryEAO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.service.CemeteryService;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class CemeteryServiceImpl extends LoggableService<Cemetery, CemeteryEAO, LogEntryService> implements CemeteryService {

    @Autowired
    private CemeteryEAO cemeteryEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Cemetery cemetery) {
        return loggedCreate(cemeteryEAO, logService, cemetery);
    }

    @Override
    public Cemetery delete(Integer userId, Integer id) {
        return loggedDelete(cemeteryEAO, logService, userId, id);
    }

    @Override
    public Cemetery update(Cemetery cemetery) {
        return loggedUpdate(cemeteryEAO, logService, cemetery);
    }

    @Override
    public Cemetery findById(Integer id) {
        return cemeteryEAO.findById(id);
    }

    @Override
    public List<Cemetery> findByFilter(Filter filter) {
        return cemeteryEAO.findByFilter(filter);
    }
}
