package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.Deceased;

import java.util.List;

/**
 * Created by amalia on 11/27/2014.
 */
public interface DeceasedService {
    public Integer create(Deceased entity);
    public Deceased delete(Integer id);
    public Deceased update(Deceased entity);
    public Deceased findById(Integer id);
    public List<Deceased > findByFilter();
}
