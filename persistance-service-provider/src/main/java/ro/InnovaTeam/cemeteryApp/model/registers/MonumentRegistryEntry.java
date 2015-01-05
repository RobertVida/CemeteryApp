package ro.InnovaTeam.cemeteryApp.model.registers;

import java.util.Date;

/**
 * Created by robert on 1/5/2015.
 */
public class MonumentRegistryEntry extends RegistryEntry{

    private Integer cemeteryId;
    private String cemeteryName;
    private Integer parcelId;
    private String parcelName;
    private Integer monumentId;

    private String ownerFirstName;
    private String ownerLastName;
    private String ownerAddress;

    private Integer receiptNumber;
    private String deceasedFirstName;
    private String deceasedLastName;
    private Date burialDate;
    private Double surface;

    public MonumentRegistryEntry() {
    }

    public Integer getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(Integer cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public String getCemeteryName() {
        return cemeteryName;
    }

    public void setCemeteryName(String cemeteryName) {
        this.cemeteryName = cemeteryName;
    }

    public Integer getParcelId() {
        return parcelId;
    }

    public void setParcelId(Integer parcelId) {
        this.parcelId = parcelId;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public Integer getMonumentId() {
        return monumentId;
    }

    public void setMonumentId(Integer monumentId) {
        this.monumentId = monumentId;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public Integer getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Integer receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getDeceasedFirstName() {
        return deceasedFirstName;
    }

    public void setDeceasedFirstName(String deceasedFirstName) {
        this.deceasedFirstName = deceasedFirstName;
    }

    public String getDeceasedLastName() {
        return deceasedLastName;
    }

    public void setDeceasedLastName(String deceasedLastName) {
        this.deceasedLastName = deceasedLastName;
    }

    public Date getBurialDate() {
        return burialDate;
    }

    public void setBurialDate(Date burialDate) {
        this.burialDate = burialDate;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }
}
