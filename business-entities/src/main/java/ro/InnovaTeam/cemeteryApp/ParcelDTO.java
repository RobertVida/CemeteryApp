package ro.InnovaTeam.cemeteryApp;

import ro.InnovaTeam.cemeteryApp.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class ParcelDTO extends BaseDTO{

    @NotNull
    private String name;
    @NotNull
    private Integer cemeteryId;
//    private StructureDTO structure;

    public ParcelDTO() {
    }

    public ParcelDTO(Integer cemeteryId, String name) {
        this.cemeteryId = cemeteryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(Integer cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

//    public Structure getStructure() {
//        return structure;
//    }
//
//    public void setStructure(Structure structure) {
//        this.structure = structure;
//    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cemeteryId=" + cemeteryId +
//                ", structure=" + structure +
                '}';
    }
}