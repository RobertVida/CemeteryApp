package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.Deceased;

import java.util.List;

/**
 * Created by amalia on 11/27/2014.
 */
public interface DeceasedEAO {

    public Integer create(Deceased deceased);

    public Deceased delete(Integer id);

    public Deceased update(Deceased deceased);

    public Deceased findById(Integer id);

    public List<Deceased> findByFilter();
}
