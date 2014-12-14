package ro.InnovaTeam.cemeteryApp.model;

import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
public class Error {

    private List<String> errors;

    public Error() {
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "Error{" +
                "errors=" + errors +
                '}';
    }
}
