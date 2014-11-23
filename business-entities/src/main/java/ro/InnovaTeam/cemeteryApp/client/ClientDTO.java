package ro.InnovaTeam.cemeteryApp.client;


import ro.InnovaTeam.cemeteryApp.BaseDTO;

/**
 * Created by Catalin Sorecau on 11/23/2014.
 */
public class ClientDTO extends BaseDTO {

    private String firstName;

    private String lastName;

    private String cnp;

    private String phoneNumber;

    private String address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
