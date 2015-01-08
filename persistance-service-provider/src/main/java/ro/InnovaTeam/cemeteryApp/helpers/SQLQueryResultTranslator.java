package ro.InnovaTeam.cemeteryApp.helpers;

import ro.InnovaTeam.cemeteryApp.model.registers.*;

import java.util.Date;

/**
 * Created by robert on 1/5/2015.
 */
public class SQLQueryResultTranslator {

    public static <T extends RegistryEntry> T translate(Object[] e, Class<T> clazz) {
        if (clazz.equals(BurialRegistryEntry.class)) {
            return clazz.cast(translateBurialRegistryEntry(e));
        } else if (clazz.equals(GraveRegistryEntry.class)) {
            return clazz.cast(translateGraveRegistryEntry(e));
        } else if (clazz.equals(MonumentRegistryEntry.class)) {
            return clazz.cast(translateMonumentRegistryEntry(e));
        } else if (clazz.equals(DeceasedRegistryEntry.class)) {
            return clazz.cast(translateDeceasedRegistryEntry(e));
        } else if (clazz.equals(DeceasedNoCaregiverRegistryEntry.class)) {
            return clazz.cast(translateDeceasedNoCaregiverEntry(e));
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

    private static GraveRegistryEntry translateGraveRegistryEntry(Object[] e) {
        GraveRegistryEntry entry = new GraveRegistryEntry();
        entry.setCemeteryId((Integer) e[0]);
        entry.setCemeteryName((String) e[1]);
        entry.setParcelId((Integer) e[2]);
        entry.setParcelName((String) e[3]);
        entry.setGraveId((Integer) e[4]);
        entry.setOwnerFirstName((String) e[5]);
        entry.setOwnerLastName((String) e[6]);
        entry.setOwnerAddress((String) e[7]);
        entry.setReceiptNumber((Integer) e[8]);
        entry.setDeceasedFirstName((String) e[9]);
        entry.setDeceasedLastName((String) e[10]);
        entry.setBurialDate((Date) e[11]);
        entry.setSurface((Double) e[12]);

        return entry;
    }

    private static MonumentRegistryEntry translateMonumentRegistryEntry(Object[] e) {
        MonumentRegistryEntry entry = new MonumentRegistryEntry();
        entry.setCemeteryId((Integer) e[0]);
        entry.setCemeteryName((String) e[1]);
        entry.setParcelId((Integer) e[2]);
        entry.setParcelName((String) e[3]);
        entry.setMonumentId((Integer) e[4]);
        entry.setOwnerFirstName((String) e[5]);
        entry.setOwnerLastName((String) e[6]);
        entry.setOwnerAddress((String) e[7]);
        entry.setReceiptNumber((Integer) e[8]);
        entry.setDeceasedFirstName((String) e[9]);
        entry.setDeceasedLastName((String) e[10]);
        entry.setBurialDate((Date) e[11]);
        entry.setSurface((Double) e[12]);

        return entry;
    }

    private static DeceasedRegistryEntry translateDeceasedRegistryEntry(Object[] e) {
        DeceasedRegistryEntry entry = new DeceasedRegistryEntry();
        entry.setDeceasedId((Integer) e[0]);
        entry.setFirstName((String) e[1]);
        entry.setLastName((String) e[2]);
        entry.setCemeteryId((Integer) e[3]);
        entry.setCemeteryName((String) e[4]);
        entry.setParcelId((Integer) e[5]);
        entry.setParcelName((String) e[6]);
        entry.setGraveId((Integer) e[7]);
        entry.setBuriedOn((Date) e[8]);

        return entry;
    }

    private static DeceasedNoCaregiverRegistryEntry translateDeceasedNoCaregiverEntry(Object[] e) {
        DeceasedNoCaregiverRegistryEntry entry = new DeceasedNoCaregiverRegistryEntry();
        entry.setDeceasedId((Integer) e[0]);
        entry.setFirstName((String) e[1]);
        entry.setLastName((String) e[2]);
        entry.setCemeteryId((Integer) e[3]);
        entry.setCemeteryName((String) e[4]);
        entry.setParcelId((Integer) e[5]);
        entry.setParcelName((String) e[6]);
        entry.setGraveId((Integer) e[7]);
        entry.setBuriedOn((Date) e[8]);
        entry.setCertificateId((Integer) e[9]);
        entry.setRequestIMLid((Integer) e[10]);

        return entry;
    }
}
