package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.GraveEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Grave;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.GraveService;

import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
@Transactional
@Service
public class GraveServiceImpl implements GraveService {

    @Autowired
    private GraveEAO graveEAO;

    @Override
    public Integer create(Grave grave) {
        return graveEAO.create(grave);
    }

    @Override
    public Grave delete(Integer id) {
        //ToDo delete deceased
        return graveEAO.delete(id);
    }

    @Override
    public Grave update(Grave grave) {
        return graveEAO.update(grave);
    }

    @Override
    public Grave findById(Integer id) {
        return graveEAO.findById(id);
    }

    @Override
    public List<Grave> findByFilter(Filter filter) {
        return graveEAO.findByFilter(filter);
    }
}
