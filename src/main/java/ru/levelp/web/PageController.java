package ru.levelp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(path = "/")
    public String index(){
        return "page";
    }
}
