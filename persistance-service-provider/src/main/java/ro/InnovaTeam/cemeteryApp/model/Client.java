package ro.InnovaTeam.cemeteryApp.model;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Client extends BaseEntity {

    private String firstName;
    private String lastName;
    private String cnp;
    private String phoneNumber;
    private String address;

    public Client() {
        super("clients");
    }

    public Client(String firstName, String lastName, String cnp, String phoneNumber, String address) {
        super("clients");
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cnp='" + cnp + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
