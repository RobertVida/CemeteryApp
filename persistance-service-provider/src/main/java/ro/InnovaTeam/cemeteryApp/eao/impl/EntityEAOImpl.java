package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.EntityEAO;
import ro.InnovaTeam.cemeteryApp.helpers.FilteredQueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

/**
 * Created by robert on 11/22/2014.
 */
@Component
@SuppressWarnings("unchecked")
public abstract class EntityEAOImpl<Entity> implements EntityEAO<Entity> {

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Override
    public Integer create(String tableName, Entity entity) {
        return (Integer) getSession().save(tableName, entity);
    }

    @Override
    public Entity delete(String tableName, Integer id) {
        Entity entity = (Entity) getSession().get(tableName, id);
        getSession().delete(tableName, entity);
        return entity;
    }

    @Override
    public void update(String tableName, Entity entity) {
        getSession().update(tableName, entity);
    }

    @Override
    public Entity findById(String tableName, Integer id) {
        return (Entity) getSession().get(tableName, id);
    }

    @Override
    public List<Entity> findByFilter(String tableName, Filter filter) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .build(getSession()).list();
    }

    protected void flush() {
        getSession().flush();
        getSession().clear();
    }
}
