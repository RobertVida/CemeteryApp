package ro.InnovaTeam.cemeteryApp.model;

import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Monument extends Structure {

	private String name;
	private String description;
	
	public Monument(long id, long parcelId, Date createdOn, String type,
			int width, int length, String name, String description) {
		super(id, parcelId, createdOn, type, width, length);
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
}
