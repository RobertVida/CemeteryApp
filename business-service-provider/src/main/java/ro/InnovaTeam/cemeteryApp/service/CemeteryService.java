package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.Cemetery;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
public interface CemeteryService {
    public Integer create(Cemetery entity);
    public Cemetery delete(Integer id);
    public Cemetery update(Cemetery entity);
    public Cemetery findById(Integer id);
    public List<Cemetery > findByFilter();
}
