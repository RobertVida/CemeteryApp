package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;
import ro.InnovaTeam.cemeteryApp.exceptions.Forbidden;
import ro.InnovaTeam.cemeteryApp.exceptions.UnauthorizedAccess;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by robert on 1/8/2015.
 */
public class ExceptionHandledController {

    HttpHeaders headers = new HttpHeaders() {{
        set("Content-Type", "application/json");
    }};

    @ExceptionHandler(Forbidden.class)
     public ResponseEntity<ErrorDTO> handle(Forbidden e) {
        return new ResponseEntity<ErrorDTO>(
                new ErrorDTO() {{
                    setStatus(Status.FORBIDDEN.toString());
                    add("Nu ai drepturile necesare.");
                }}, headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedAccess.class)
    public ResponseEntity<ErrorDTO> handle(UnauthorizedAccess e) {
        return new ResponseEntity<ErrorDTO>(
                new ErrorDTO() {{
                    setStatus(Status.UNAUTHORIZED_ACCESS.toString());
                    add("Logheaza-te mai intai.");
                }}, headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(final MethodArgumentNotValidException e) {
        return new ResponseEntity<ErrorDTO>(
                new ErrorDTO() {{
                    setStatus(Status.VALIDATION_ERROR.toString());
                    for (ObjectError error : e.getBindingResult().getAllErrors()) {
                        add(error.getDefaultMessage());
                    }
                }}, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handle(Exception e) {
        return new ResponseEntity<ErrorDTO>(
                new ErrorDTO() {{
                    setStatus(Status.UNKNOWN_ERROR.toString());
                    add("Eroare neasteptata. Incearca mai tarziu.");
                }}, headers, INTERNAL_SERVER_ERROR);
    }
}
