package ro.InnovaTeam.cemeteryApp.helpers;

/**
 * Created by robert on 12/22/2014.
 */
public class ColumnConstraintBuilder {

    public static class ColumnConstraintSolver {

        private String column;

        public ColumnConstraintSolver(String column) {
            this.column = column;
        }

        public String is(Object value){
            return value != null ? toSQL(column, "=", value) : null;
        }

        public String like(Object value){
            return value != null ? toSQL(column, "LIKE", "'" + value + "'") : null;
        }

        public String isNot(Object value){
            return value != null ? toSQL(column, "!=", value) : null;
        }

        public String isLessThan(Object value){
            return value != null ? toSQL(column, "<", value) : null;
        }

        public String isLessThanOrEqual(Object value){
            return value != null ? toSQL(column, "<=", value) : null;
        }

        public String isGreaterThan(Object value){
            return value != null ? toSQL(column, ">", value) : null;
        }

        public String isGreaterThanOrEqual(Object value){
            return value != null ? toSQL(column, ">=", value) : null;
        }

        public String isBetween(Object value1, Object value2){
            return value1 != null && value2 != null ? isGreaterThan(value1) + " AND " + isLessThan(value2) : null;
        }

        private String toSQL(String column, String symbol, Object value){
            return String.format(" %s %s %s ", column, symbol, value);
        }
    }

    public static ColumnConstraintSolver column(String column){
        return new ColumnConstraintSolver(column);
    }
}
