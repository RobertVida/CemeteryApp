package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    public String getContent() {
        return "";
    }
}