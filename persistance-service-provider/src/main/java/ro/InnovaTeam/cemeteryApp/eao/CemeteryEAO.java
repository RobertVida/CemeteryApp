package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.Cemetery;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
public interface CemeteryEAO {

    public Integer create(Cemetery cemetery);

    public Cemetery delete(Integer id);

    public Cemetery update(Cemetery cemetery);

    public Cemetery findById(Integer id);

    public List<Cemetery> findByFilter();
}
