package ro.InnovaTeam.cemeteryApp.eao;

import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by robert on 11/18/2014.
 */
@Component
public class BaseEAO {

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    protected String makeSelectQuery(String table){
        return "from " + table;
    }

    protected void flush() {
        getSession().flush();
    }
}
