package ro.InnovaTeam.cemeteryApp.helpers;

import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;

/**
 * Created by robert on 12/22/2014.
 */
public class AndWithOrRestrictionBuilder {

    public static class AndWithOrRestrictionResolver{

        private String[] criteria;

        private AndWithOrRestrictionResolver(String[] criteria) {
            this.criteria = criteria;
        }

        public String areAtLeastOnceInAnyOf(String... container){
            return formConjunction(new StringBuilder(), container);
        }

        private String formConjunction(StringBuilder conjunction, String...container) {
            for(int i = 0 ; i < criteria.length ; i++){
                conjunction.append( i != 0 ? " AND " : "");
                conjunction.append("(")
                        .append(formDisjunction(new StringBuilder(), criteria[i], container))
                        .append(")");
            }
            return conjunction.toString();
        }

        private String formDisjunction(StringBuilder disjunction, String c, String...container) {
            for(int i = 0 ; i < container.length ; i++){
                disjunction.append( i != 0 ? " OR " : "");
                disjunction.append(column(container[i]).like("%" + c + "%"));

            }
            return disjunction.toString();
        }
    }

    public static AndWithOrRestrictionResolver allOf(String...criteria){
        return new AndWithOrRestrictionResolver(criteria);
    }

    public static AndWithOrRestrictionResolver allOf(String criteria){
        return criteria != null ? allOf(criteria.split(" ")) : allOf("");
    }
}
