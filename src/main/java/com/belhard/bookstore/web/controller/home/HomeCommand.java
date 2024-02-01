package com.belhard.bookstore.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeCommand{
    @GetMapping
    public String execute() {
        return "index";
    }
}
