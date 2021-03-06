package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
public interface EntityService<Entity> extends Service{
    public Integer create(Entity entity);

    public Entity delete(Integer userId, Integer id);

    public Entity update(Entity entity);

    public Entity findById(Integer id);

    public List<Entity> findByFilter(Filter filter);

    public Integer countByFilter(Filter filter);
}
