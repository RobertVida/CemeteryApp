package ro.InnovaTeam.cemeteryApp.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 11/23/2014.
 */
public class QueryBuilder {

    public static class Is {
        private QueryBuilder queryBuilder;
        private String fieldName;

        public Is(QueryBuilder queryBuilder, String fieldName) {
            this.queryBuilder = queryBuilder;
            this.fieldName = fieldName;
        }

        public QueryBuilder is(Object column) {
            queryBuilder.where.put(fieldName, column);
            return queryBuilder;
        }
    }

    private String action;
    private String table;
    private Map<String, Object> where = new HashMap<String, Object>();

    private QueryBuilder() {
    }

    public static QueryBuilder instance() {
        return new QueryBuilder();
    }

    public QueryBuilder from(String table) {
        this.action = "FROM";
        this.table = table;
        return this;
    }

    public Is where(String column) {
        return new Is(this, column);
    }

    public String build() {
        StringBuilder sb = new StringBuilder();

        addAction(sb);
        addWhere(sb);

        return sb.toString();
    }

    private void addAction(StringBuilder sb) {
        append(sb, action);
        append(sb, table);
    }

    private void addWhere(StringBuilder sb) {
        if (!hasWhereClause()) {
            return;
        }
        append(sb, "WHERE");

        boolean isFirst = true;
        for (String key : where.keySet()) {
            append(sb, key);
            append(sb, " =");
            append(sb, where.get(key));
            if (isFirst) {
                isFirst = false;
                continue;
            }
            append(sb, ",");
        }
    }

    private StringBuilder append(StringBuilder sb, Object value) {
        return sb.append(value).append(" ");
    }

    private boolean hasWhereClause() {
        return where != null && where.size() > 0;
    }
}
