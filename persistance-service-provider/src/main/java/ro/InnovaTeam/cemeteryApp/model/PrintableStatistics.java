package ro.InnovaTeam.cemeteryApp.model;

import java.util.HashMap;

/**
 * Created by robert on 1/15/2015.
 */
public class PrintableStatistics extends BaseEntity{

    public static enum Fields {
        TOTAL_CEMETERIES,
        TOTAL_PARCELS,
        AVERAGE_PARCELS_PER_CEMETERIES,
        TOTAL_STRUCTURES,
        TOTAL_GRAVES,
        TOTAL_MONUMENTS,
        AVERAGE_STRUCTURE_PER_PARCEL,
        TOTAL_DECEASED,
        DECEASED_CAREGIVER,
        DECEASED_NO_CAREGIVER,
        AVERAGE_DECEASED_STRUCTURE,
        TOTAL_CLIENTS,
        TOTAL_REQUESTS,
        REQUESTS_FAVORABLE,
        REQUESTS_NOT_FAVORABLE,
        REQUESTS_PARTIAL,
        REQUESTS_DECLINED,
        REQUESTS_INTERN,
        TOTAL_CONTRACTS,
        AVERAGE_CONTRACT_PER_CLIENT,
        TITLE
    }

    private HashMap<String, Object> data = new HashMap<>();

    public PrintableStatistics() {
    }

    public PrintableStatistics(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public Object get(Fields field) {
        return data.get(field.toString());
    }

    public void add(Fields field, Object obj) {
        data.put(field.toString(), obj);
    }
}
