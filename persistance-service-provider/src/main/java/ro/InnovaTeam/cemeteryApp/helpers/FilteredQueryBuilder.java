package ro.InnovaTeam.cemeteryApp.helpers;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 11/26/2014.
 */
public class FilteredQueryBuilder{

    private Filter filter = new Filter();
    private String table;
    private String[] criteriaSearchableColumns;
    private String parentIdColumn;
    private Map<String, String> extra = null;

    private FilteredQueryBuilder() {
        super();
    }

    public static FilteredQueryBuilder instance() {
        return new FilteredQueryBuilder();
    }

    public FilteredQueryBuilder setFilter(Filter filter){
        this.filter = filter;
        return this;
    }

    public FilteredQueryBuilder setCriteriaSearchableColumns(String... columns){
        this.criteriaSearchableColumns = columns;
        return this;
    }

    public FilteredQueryBuilder setParentIdColumn(String column){
        this.parentIdColumn = column;
        return this;
    }

    public FilteredQueryBuilder where(String column, String value){
        if(extra == null){
            extra = new HashMap<String, String>();
        }
        extra.put(column, value);
        return this;
    }

    public FilteredQueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    private void setLimits(Query query) {
        if(filter != null) {
            if (filter.getPageSize() != null) {
                query.setMaxResults(filter.getPageSize());
            }
            if (filter.getPageNo() != null) {
                query.setFirstResult((filter.getPageNo() - 1) * (filter.getPageSize() != null && filter.getPageSize() != 0 ? filter.getPageSize() : 0));
            }
        }
    }

    public Query build(Session session) {
        StringBuilder sb = new StringBuilder();

        addAction(sb);
        addWhere(sb);

        Query query = session.createQuery(sb.toString());
        setLimits(query);
        return query;
    }

    protected void addAction(StringBuilder sb) {
        sb.append(" FROM ").append(table);
    }

    private void addWhere(StringBuilder sb){
        if(hasSearchCriteria() || hasParentId() || hasExtra()){
            sb.append(" WHERE ");
        }
        if(hasSearchCriteria()){
            addSearchCriteria(sb);
        }
        if((hasSearchCriteria()) && hasParentId()){
            sb.append(" AND ");
        }
        if(hasParentId()){
            addParentIdConstraint(sb);
        }
        if((hasSearchCriteria() && hasExtra()) || (hasParentId() && hasExtra())){
            sb.append(" AND ");
        }
        if(hasExtra()){
            addExtra(sb);
        }
    }

    private void addSearchCriteria(StringBuilder sb) {
        sb.append(" ( ");
        String[] keywords = filter.getSearchCriteria().split(" ");
        for(String column : criteriaSearchableColumns){
            if(!column.equals("") && keywords.length > 0) {
                addKeywordContraintsToColumn(sb, column, keywords);
            }
        }
        sb.setLength(sb.length() - 4);
        sb.append(" ) ");
    }

    private void addKeywordContraintsToColumn(StringBuilder sb, String column, String[] keywords) {
        // ToDo : make such that a keyword should be in at least one column
        for(String keyword : keywords){
            sb.append(String.format(" %s ", column))
                    .append(" LIKE ")
                    .append("'%").append(keyword).append("%'");
            sb.append(" OR ");
        }
    }

    private void addExtra(StringBuilder sb) {
        Integer len = extra.keySet().size();
        for(String column : extra.keySet()){
            sb.append(String.format(" %s = %s ", column, extra.get(column)));
            len--;
            if(len != 0){
                sb.append(" AND ");
            }
        }
    }

    private void addParentIdConstraint(StringBuilder sb) {
        if(hasParentId()) {
            sb.append(String.format(" %s ", parentIdColumn))
                    .append(" = ").append(filter.getParentId()).append(" ");
        }
    }

    private boolean hasExtra() {
        return extra != null;
    }

    private boolean hasParentId() {
        return filter != null && parentIdColumn != null &&!parentIdColumn.equals("") && filter.getParentId() != null;
    }

    private boolean hasSearchCriteria() {
        return filter != null && filter.getSearchCriteria() != null && !filter.getSearchCriteria().equals("")
                && criteriaSearchableColumns != null && criteriaSearchableColumns.length > 0;
    }


}
