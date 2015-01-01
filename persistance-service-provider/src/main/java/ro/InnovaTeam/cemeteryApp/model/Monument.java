package ro.InnovaTeam.cemeteryApp.model;

import java.util.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Monument extends Structure {

	private String name;
	private String description;

    public Monument() {
        super("monument");
    }

    public Monument(Integer parcelId, Date createdOn, String type, Integer width, Integer length,
                    String name, String description) {
		super("monument", parcelId, createdOn, type, width, length);
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
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
