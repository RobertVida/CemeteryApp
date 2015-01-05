package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.NoCaregiverDocument;

/**
 * Created by robert on 1/5/2015.
 */
public interface NoCaregiverDocumentEAO extends EntityEAO<NoCaregiverDocument>{

    public NoCaregiverDocument findByDeceasedId(Integer id);
}
