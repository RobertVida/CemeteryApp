package ro.InnovaTeam.cemeteryApp.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
@RequestMapping("/clients")
public class ClientsController {

    @RequestMapping
    public String showDefaultView(Model model) {

        return "clientsPage";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getClientById(Model model,  @PathVariable String id) {

        return "clientDetailsPage";
    }

    private String validateInputs(String firstName, String lastName, String cnp, String phoneNumber, String address){
        String errorMsg = "";

        if(StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(cnp)
                || StringUtils.isBlank(phoneNumber) || StringUtils.isBlank(address))
            errorMsg += "Input fields cannot be empty.\n";

        if(cnp.length() != 13)
            errorMsg += "CNP doesn't contain 13.\n";
        if(!isInteger(cnp))
            errorMsg +=  "CNP cannot contain leters.\n";
        return errorMsg;


    }
    private Boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
