package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.ContractEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Contract;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;

/**
 * Created by robert on 1/2/2015.
 */
@Component
public class ContractEAOImpl extends EntityEAOImpl<Contract> implements ContractEAO {

    private static final String TABLE = "contracts";

    @Override
    public Integer create(Contract contract) {
        Integer id = create(TABLE, contract);
        flush();
        return id;
    }

    @Override
    public Contract delete(Integer id) {
        Contract contract = delete(TABLE, id);
        flush();
        return contract;
    }

    @Override
    public Contract update(Contract contract) {
        update(TABLE, contract);
        flush();
        return findById(contract.getId());
    }

    @Override
    public Contract findById(Integer id) {
        Contract contract = findById(TABLE, id);
        flush();
        return contract;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Contract> findByFilter(Filter filter) {
        return makeFilterQuery(filter)
                .build().list();
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return ((Long) makeFilterQuery(filter).count()
                .build().iterate().next()).intValue();
    }

    private QueryBuilder makeFilterQuery(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("c")
                ).where(
                        column("c.structureId").is(filter.getParentId())
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo());
    }
}
