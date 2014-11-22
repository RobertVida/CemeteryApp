package ro.InnovaTeam.cemeteryApp.model;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Monument extends Structure {

    @NotNull
	private String name;
    @NotNull
	private String description;

    public Monument() {
    }

    public Monument(Integer parcelId, Date createdOn, String type, Integer width, Integer length,
                    String name, String description) {
		super(parcelId, createdOn, type, width, length);
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public String toString() {
        return "Structure{" +
                "id=" + id +
                ", parcelId=" + getParcelId()+
                ", createdOn=" + getCreatedOn() +
                ", type='" + getType() + '\'' +
                ", width=" + getWidth() +
                ", length=" + getLength() +
                ", deceased=" + getDeceased() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
