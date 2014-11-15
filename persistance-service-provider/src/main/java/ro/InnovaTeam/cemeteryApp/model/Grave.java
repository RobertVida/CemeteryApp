package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Grave extends Structure {

    //don't write code here, it's not nice to disturb the dead

    public Grave(long id, long parcelId, Date createdOn, String type, int width, int length) {
        super(id, parcelId, createdOn, type, width, length);
    }
}
