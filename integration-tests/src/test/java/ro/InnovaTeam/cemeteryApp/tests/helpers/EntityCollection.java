package ro.InnovaTeam.cemeteryApp.tests.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 12/25/2014.
 */
public class EntityCollection {

    private List<EntityHolder> entities = new ArrayList<EntityHolder>();

    public EntityCollection() {
    }

    public EntityHolder pop() {
        if (entities.size() == 0) {
            return null;
        }

        EntityHolder entity = entities.get(entities.size() - 1);
        entities.remove(entity);
        return entity;
    }

    public void push(EntityTypes type, Integer id) {
        entities.add(new EntityHolder(type, id));
    }

    public void eliminate(EntityTypes type, Integer id) {
        EntityHolder targetEntity = new EntityHolder(type, id);
        for (int i = 0; i < entities.size(); i++) {
            if (targetEntity.compareTo(entities.get(i)) == 0) {
                entities.remove(entities.get(i));
                return;
            }
        }
    }

}
