package ro.InnovaTeam.cemeteryApp.tests.helpers;

/**
 * Created by robert on 12/25/2014.
 */
public class EntityHolder implements Comparable<EntityHolder>{

    private final EntityTypes type;
    private final Integer id;

    public EntityHolder(EntityTypes type, Integer id) {
        this.type = type;
        this.id = id;
    }

    public EntityTypes getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(EntityHolder o) {
        return type.toString().equals(o.type.toString())
                && id.equals(o.id) ? 0 : -1;
    }
}
