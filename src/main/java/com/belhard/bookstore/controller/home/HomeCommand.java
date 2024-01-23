package com.belhard.bookstore.controller.home;

import com.belhard.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("home")
public class HomeCommand implements Command {
    public String execute(HttpServletRequest req) {
        return "jsp/index.jsp";
    }
}
