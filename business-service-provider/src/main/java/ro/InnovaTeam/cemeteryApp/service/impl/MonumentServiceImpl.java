package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.MonumentEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Monument;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;
import ro.InnovaTeam.cemeteryApp.service.MonumentService;

import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
@Transactional
@Service
public class MonumentServiceImpl extends LoggableService<Monument, MonumentEAO, LogEntryService> implements MonumentService {

    @Autowired
    private MonumentEAO monumentEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Monument monument) {
        return loggedCreate(monumentEAO, logService, monument);
    }

    @Override
    public Monument delete(Integer userId, Integer id) {
        //ToDo delete deceased
        return loggedDelete(monumentEAO, logService, userId, id);
    }

    @Override
    public Monument update(Monument monument) {
        return loggedUpdate(monumentEAO, logService, monument);
    }

    @Override
    public Monument findById(Integer id) {
        return monumentEAO.findById(id);
    }

    @Override
    public List<Monument> findByFilter(Filter filter) {
        return monumentEAO.findByFilter(filter);
    }
}
