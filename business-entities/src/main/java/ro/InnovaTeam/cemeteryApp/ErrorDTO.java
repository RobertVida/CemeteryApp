package ro.InnovaTeam.cemeteryApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
public class ErrorDTO extends BaseDTO{

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

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "errors=" + errors +
                '}';
    }
}
