package ro.InnovaTeam.cemeteryApp.model;

import java.util.HashMap;

/**
 * Created by robert on 1/15/2015.
 */
public class PrintableContract extends BaseEntity{

    public static enum Fields {
        CONTRACT_ID,
        CONTRACT_SIGNED_ON,
        CONTRACT_EXPIRES_ON,
        CLIENT_FNAME,
        CLIENT_LNAME,
        CLIENT_CNP,
        CLIENT_ADDRESS,
        CEMETERY_NAME,
        PARCEL_NAME,
        STRUCTURE_ID,
        STRUCTURE_SIZE,
        REQUEST_ID,
        REQUEST_CREATED_ON,
        REQUEST_INFOCET,
        TITLE
    }

    private HashMap<String, Object> data = new HashMap<>();

    public PrintableContract() {
    }

    public PrintableContract(HashMap<String, Object> data) {
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
