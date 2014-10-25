package ro.InnovaTeam.cemeteryApp.eao.test.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.test.TestEAO;
import ro.InnovaTeam.cemeteryApp.model.test.Test;

/**
 * Created by Cata on 10/24/2014.
 */
@Component
public class TestEAOImpl implements TestEAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    private Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Override
    public Test getForId(Long id) {
        return (Test) getSession().createCriteria(Test.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
}
