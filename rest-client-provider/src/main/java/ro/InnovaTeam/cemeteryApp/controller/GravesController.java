package ro.InnovaTeam.cemeteryApp.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tudor on 11/22/2014.
 */
@Controller
@RequestMapping("/graves")
public class GravesController {
    @RequestMapping
    public String showDefaultView(Model model) {

        return "gravesPage";
    }
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getGraveById(Model model,  @PathVariable String id) {

        return "graveDetailsPage";
    }

    private String validateInputs(String createdOn, String type, String width, String length){
        String errorMsg = "";

        if(StringUtils.isBlank(createdOn) || StringUtils.isBlank(type) || StringUtils.isBlank(width)
                || StringUtils.isBlank(length) )
            errorMsg += "Input fields cannot be empty.\n";

        return errorMsg;


    }

}
