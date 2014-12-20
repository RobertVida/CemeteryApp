package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.DeceasedEAO;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.service.DeceasedService;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class DeceasedServiceImpl extends LoggableService<Deceased, DeceasedEAO, LogEntryService> implements DeceasedService {

    @Autowired
    private DeceasedEAO deceasedEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Deceased deceased) {
        return loggedCreate(deceasedEAO, logService, deceased);
    }

    @Override
    public Deceased delete(Integer userId, Integer id) {
        //Todo make atomic
        return loggedDelete(deceasedEAO, logService, userId, id);
    }

    @Override
    public Deceased update(Deceased deceased) {
        return loggedUpdate(deceasedEAO, logService, deceased);
    }

    @Override
    public Deceased findById(Integer id) {
        return deceasedEAO.findById(id);
    }

    @Override
    public List<Deceased> findByFilter(Filter filter) {
        return deceasedEAO.findByFilter(filter);
    }
}
