package ro.InnovaTeam.cemeteryApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
public class ErrorDTO extends BaseDTO{

    public enum Status{
        UNAUTHORIZED_ACCESS,
        VALIDATION_ERROR,
        UNKNOWN_ERROR,
        BAD_CREDENTIALS,
        FORBIDDEN
    }

    private String status;
    private List<String> errors;

    public ErrorDTO() {
    }

    public ErrorDTO(List<String> errors) {
        this.errors = errors;
    }

    public void add(String error) {
        if(errors == null) {
            errors = new ArrayList<String>();
        }
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "status='" + status + '\'' +
                ", errors=" + errors +
                '}';
    }
}
