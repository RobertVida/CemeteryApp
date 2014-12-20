package ro.InnovaTeam.cemeteryApp.service.impl;

import ro.InnovaTeam.cemeteryApp.eao.EntityEAO;
import ro.InnovaTeam.cemeteryApp.model.BaseEntity;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

/**
 * Created by robert on 12/15/2014.
 */
public class LoggableService<ENTITY extends BaseEntity, EAO extends EntityEAO<ENTITY>, LOGGER extends LogEntryService> {

    public Integer loggedCreate(EAO eao, LOGGER logger, ENTITY entity){
        Integer id = eao.create(entity);
        logger.logCreate(entity);
        return id;
    }

    public ENTITY loggedDelete(EAO eao, LOGGER logger, Integer userId, Integer id){
        ENTITY entity = eao.delete(id);
        logger.logDelete(userId, entity);
        return entity;
    }

    public ENTITY loggedUpdate(EAO eao, LOGGER logger, ENTITY entity){
        ENTITY oldEntity = eao.findById(entity.getId());
        ENTITY newEntity = eao.update(entity);

        newEntity.setUserId(entity.getUserId());
        logger.logUpdate(oldEntity, newEntity);
        return newEntity;
    }
}
