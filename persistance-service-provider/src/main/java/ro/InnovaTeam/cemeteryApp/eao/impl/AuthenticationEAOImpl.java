package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.AuthenticationEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.User;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;

/**
 * Created by robert on 1/12/2015.
 */
@Component
public class AuthenticationEAOImpl implements AuthenticationEAO {

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Override
    public User getUser(String username, String password) {
        return (User)QueryBuilder.instance(getSession())
                .select(
                        from("users").as("u")
                ).where(
                        and(
                                column("u.username").like(username),
                                column("u.password").like(password)
                        )
                )
                .setMaxResults(20)
                .setFirstResult(1)
                .build().list().get(0);
    }

    @Override
    public User getUser(String token) {
        return (User)QueryBuilder.instance(getSession())
                .select(
                        from("users").as("u")
                ).where(
                        and(
                                column("u.token").like(token)
                        )
                )
                .setMaxResults(20)
                .setFirstResult(1)
                .build().list().get(0);
    }

    @Override
    public void createTokenForUser(User user) {
        getSession().update("users", user);
        flush();
    }

    protected void flush() {
        getSession().flush();
        getSession().clear();
    }
}
