package ro.InnovaTeam.cemeteryApp.tests.helpers;

/**
 * Created by robert on 11/23/2014.
 */
public class EntityLoader {
    private String id;
    private Integer value;
    private String createFile;
    private String deleteFile;

    public EntityLoader() {
    }

    public EntityLoader(String id, Integer value, String createFile, String deleteFile) {
        this.id = id;
        this.value = value;
        this.createFile = createFile;
        this.deleteFile = deleteFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCreateFile() {
        return createFile;
    }

    public void setCreateFile(String createFile) {
        this.createFile = createFile;
    }

    public String getDeleteFile() {
        return deleteFile;
    }

    public void setDeleteFile(String deleteFile) {
        this.deleteFile = deleteFile;
    }
}
