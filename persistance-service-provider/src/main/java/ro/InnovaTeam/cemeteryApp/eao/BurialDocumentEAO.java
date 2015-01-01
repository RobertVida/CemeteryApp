package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.BurialDocument;

/**
 * Created by robert on 12/31/2014.
 */
public interface BurialDocumentEAO extends EntityEAO<BurialDocument>{

    public BurialDocument findByDeceasedId(Integer id);
}
