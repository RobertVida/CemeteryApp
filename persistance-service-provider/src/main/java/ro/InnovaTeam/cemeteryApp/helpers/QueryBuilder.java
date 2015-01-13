package ro.InnovaTeam.cemeteryApp.helpers;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.AliasSelector;

/**
 * Created by robert on 12/22/2014.
 */
public class QueryBuilder {

    private Session session;
    private String select = "";
    private String where = "";
    private String orderBy = "";
    private Boolean count = false;

    private Integer maxResults;
    private Integer resultOffset;

    private QueryBuilder(Session session) {
        this.session = session;
    }

    public static QueryBuilder instance(Session session){
        return new QueryBuilder(session);
    }

    public QueryBuilder select(AliasSelector alias){
        select = alias.getSQL();
        return this;
    }

    public QueryBuilder where(String...where){
        StringBuilder sb = new StringBuilder();
        for(String e : where){
            if(e != null) {
                sb.append(e).append(" ");
            }
        }
        this.where = sb.toString();
        return this;
    }

    public QueryBuilder orderBy(String orderBy){
        this.orderBy = orderBy;
        return this;
    }

    public QueryBuilder count(){
        return count(true);
    }

    public QueryBuilder count(Boolean count){
        this.count = count;
        return this;
    }

    public QueryBuilder setMaxResults(int maxResults){
        this.maxResults = maxResults;
        return this;
    }

    public QueryBuilder setFirstResult(int firstResults){
        this.resultOffset = firstResults;
        return this;
    }

    public Query build(){
        prepareStatement();

        Query query = session.createQuery(select + where + orderBy);
        query.setMaxResults(maxResults);
        query.setFirstResult((resultOffset-1)*maxResults);
        return query;
    }

    private void prepareStatement() {
        select = select != null && !select.equals("") ? " FROM " + select : "";
        select = count ? " SELECT COUNT(*) " + select : select;
        where = where != null && !where.equals("") ? " WHERE " + where : "";
        orderBy = orderBy != null && !orderBy.equals("") ? " ORDER BY " + orderBy : "";
    }
}
