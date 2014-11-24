package ro.InnovaTeam.cemeteryApp.tests.helpers;

import java.util.List;

/**
 * Created by robert on 11/23/2014.
 */
public class EntityHolder {

    public static enum Entity {
        CEMETERY_ID_1,
        CEMETERY_ID_2,
        PARCEL_ID_1,
        PARCEL_ID_2
    }

    private List<EntityLoader> entities;

    public EntityHolder(List<EntityLoader> entities) {
        this.entities = entities;
    }

    public List<EntityLoader> getEntities() {
        return entities;
    }
}
