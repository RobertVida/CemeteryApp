package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.RestingPlaceRequestEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.RestingPlaceRequest;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;

/**
 * Created by robert on 11/18/2014.
 */
@Component
public class RestingPlaceRequestEAOImpl extends EntityEAOImpl<RestingPlaceRequest> implements RestingPlaceRequestEAO {

    private static final String TABLE = "restingplacerequests";

    @Override
    public Integer create(RestingPlaceRequest request) {
        Integer id = create(TABLE, request);
        flush();
        return id;
    }

    @Override
    public RestingPlaceRequest delete(Integer id) {
        RestingPlaceRequest request = delete(TABLE, id);
        flush();
        return request;
    }

    @Override
    public RestingPlaceRequest update(RestingPlaceRequest request) {
        update(TABLE, request);
        flush();
        return findById(request.getId());
    }

    @Override
    public RestingPlaceRequest findById(Integer id) {
        RestingPlaceRequest request = findById(TABLE, id);
        flush();
        return request;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RestingPlaceRequest> findByFilter(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("r")
                ).where(
                        and(
                                allOf(filter.getSearchCriteria())
                                        .areAtLeastOnceInAnyOf("r.infocetNumber"),
                                column("r.clientId").is(filter.getParentId())
                        )
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RestingPlaceRequest> findByFilterAndStatus(Filter filter, String status) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("r")
                ).where(
                        and(
                                allOf(filter.getSearchCriteria())
                                        .areAtLeastOnceInAnyOf("r.infocetNumber"),
                                column("r.clientId").is(filter.getParentId()),
                                column("r.status").like(status)
                        )
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }
}
