package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Grave extends Structure {

    //don't write code here, it's not nice to disturb the dead

    //yes but all work and no play makes Jack a dull boy

    public Grave() {
    }

    public Grave(Integer parcelId, Date createdOn, String type, Integer width, Integer length) {
        super(parcelId, createdOn, type, width, length);
    }
}
