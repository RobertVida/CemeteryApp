package ro.InnovaTeam.cemeteryApp.eao;

import java.util.List;

/**
 * Created by robert on 11/22/2014.
 */
public interface EntityEAO<Entity> {

    public Integer create(String tableName, Entity entity);

    public Entity delete(String tableName, Integer id);

    public void update(String tableName, Entity entity);

    public Entity findById(String tableName, Integer id);

    public List<Entity> findByFilter(String entityName);
}
