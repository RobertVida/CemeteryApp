package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by robert on 1/8/2015.
 */
public class ExceptionHandledController {

    HttpHeaders headers = new HttpHeaders(){{
        set("Content-Type", "application/json");
    }};

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(final MethodArgumentNotValidException e) {
        return new ResponseEntity<ErrorDTO>(
                new ErrorDTO(){{
                    for(ObjectError error : e.getBindingResult().getAllErrors()) {
                        add(error.getDefaultMessage());
                    }
                }}, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handle(Exception e) {
        return new ResponseEntity<ErrorDTO>(
                new ErrorDTO(){{
                    add("Eroare neasteptata. Incearca mai tarziu.");
                }}, headers, INTERNAL_SERVER_ERROR);
    }
}
