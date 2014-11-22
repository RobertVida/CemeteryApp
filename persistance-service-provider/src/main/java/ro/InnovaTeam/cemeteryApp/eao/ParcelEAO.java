package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.Parcel;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
public interface ParcelEAO extends EntityEAO<Parcel>{

    public List<Parcel> findByCemeteryId(Integer cemeteryId);

}
