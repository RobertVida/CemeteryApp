package ro.InnovaTeam.cemeteryApp;

import org.hibernate.validator.constraints.NotBlank;
import ro.InnovaTeam.cemeteryApp.validators.SimpleCnp;
import ro.InnovaTeam.cemeteryApp.validators.TelephoneNumber;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by Catalin Sorecau on 11/23/2014.
 */
public class ClientDTO extends BaseDTO {

    @NotBlank(message = CLIENT_FIRST_NAME_BLANK)
    private String firstName;
    @NotBlank(message = CLIENT_LAST_NAME_BLANK)
    private String lastName;
    @SimpleCnp(message = CLIENT_CNP_INVALID)
    private String cnp;
    @TelephoneNumber(message = CLIENT_PHONE_NUMBER_INVALID)
    private String phoneNumber;
    @NotBlank(message = CLIENT_ADDRESS_BLANK)
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

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cnp='" + cnp + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
