package ro.InnovaTeam.cemeteryApp.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 12/23/2014.
 */
public abstract class ConstraintWrapper {

    protected ConstraintWrapper() {
    }

    public static class OrConstraintWrapper extends ConstraintWrapper{

        private OrConstraintWrapper() {
        }

        public static String or(String...constraints){
            return new AndConstraintWrapper().getSQL(" OR ", constraints);
        }
    }

    public static class AndConstraintWrapper extends ConstraintWrapper{

        private AndConstraintWrapper() {
        }

        public static String and(String...constraints){
            return new AndConstraintWrapper().getSQL(" AND ", constraints);
        }
    }

    protected String getSQL(String linker, String...constraints){
        StringBuilder sb = new StringBuilder();

        constraints = eliminateNulls(constraints);
        for(int i = 0 ; i < constraints.length ; i++){
            sb.append( i == 0 ? "" : linker);
            sb.append(constraints[i]);
        }

        return "( " + sb.toString() + " )";
    }

    private String[] eliminateNulls(String[] constraints) {
        List<String> list = new ArrayList<String>();
        for(int i = 0 ; i < constraints.length ; i++){
            if(constraints[i] != null){
                list.add(constraints[i]);
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
