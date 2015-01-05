package ro.InnovaTeam.cemeteryApp.helpers;

import ro.InnovaTeam.cemeteryApp.model.registers.BurialRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.RegistryEntry;

import java.util.Date;

/**
 * Created by robert on 1/5/2015.
 */
public class SQLQueryResultTranslator {

    @SuppressWarnings("unchecked")
    public static <T extends RegistryEntry> T translate(Object[] e, Class<T> clazz){
        if(clazz.equals(BurialRegistryEntry.class)){
            return (T)translateBurialRegistryEntry(e);
        }
        return null;
    }

    private static BurialRegistryEntry translateBurialRegistryEntry(Object[] e) {
        BurialRegistryEntry entry = new BurialRegistryEntry();
        entry.setDeceasedId((Integer) e[0]);
        entry.setFirstName((String) e[1]);
        entry.setLastName((String) e[2]);
        entry.setReligion((String) e[3]);
        entry.setBuriedOn((Date) e[4]);
        entry.setParcelId((Integer) e[5]);
        entry.setParcelName((String) e[6]);

        return entry;
    }
}
