package ro.InnovaTeam.cemeteryApp;

import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
public class ErrorDTO {

    private List<String> errors;

    public ErrorDTO() {
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "errors=" + errors +
                '}';
    }
}
