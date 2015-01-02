package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.GraveEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Grave;
import ro.InnovaTeam.cemeteryApp.service.GraveService;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
@Transactional
@Service
public class GraveServiceImpl extends LoggableService<Grave, GraveEAO, LogEntryService> implements GraveService {

    @Autowired
    private GraveEAO graveEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Grave grave) {
        return loggedCreate(graveEAO, logService, grave);
    }

    @Override
    public Grave delete(Integer userId, Integer id) {
        return loggedDelete(graveEAO, logService, userId, id);
    }

    @Override
    public Grave update(Grave grave) {
        return loggedUpdate(graveEAO, logService, grave);
    }

    @Override
    public Grave findById(Integer id) {
        return graveEAO.findById(id);
    }

    @Override
    public List<Grave> findByFilter(Filter filter) {
        return graveEAO.findByFilter(filter);
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return graveEAO.countByFilter(filter);
    }
}
