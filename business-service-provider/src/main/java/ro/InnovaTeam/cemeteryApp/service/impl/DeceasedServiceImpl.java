package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.DeceasedEAO;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.service.DeceasedService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class DeceasedServiceImpl implements DeceasedService {

    @Autowired
    private DeceasedEAO deceasedEAO;

    @Override
    public Integer create(Deceased deceased) {
        return deceasedEAO.create(deceased);
    }

    @Override
    public Deceased delete(Integer id) {
        //Todo make atomic
        return deceasedEAO.delete(id);
    }

    @Override
    public Deceased update(Deceased deceased) {
        return deceasedEAO.update(deceased);
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
