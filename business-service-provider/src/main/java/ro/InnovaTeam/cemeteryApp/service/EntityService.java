package ro.InnovaTeam.cemeteryApp.service;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
public interface EntityService<Entity> {
    public Integer create(Entity entity);

    public Entity delete(Integer id);

    public Entity update(Entity entity);

    public Entity findById(Integer id);

    public List<Entity> findByFilter();
}
