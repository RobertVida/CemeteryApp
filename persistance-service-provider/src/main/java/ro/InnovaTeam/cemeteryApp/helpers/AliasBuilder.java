package ro.InnovaTeam.cemeteryApp.helpers;

/**
 * Created by robert on 12/22/2014.
 */
public class AliasBuilder {

    public static class AliasSelector {

        private String table;
        private String alias;

        public AliasSelector(String table) {
            this.table = table;
        }

        public AliasSelector as(String alias){
            this.alias = alias;
            return this;
        }

        public String getSQL(){
            return String.format(" %s AS %s ", table, alias);
        }
    }

    public static AliasSelector from(String table){
        return new AliasSelector(table);
    }
}
