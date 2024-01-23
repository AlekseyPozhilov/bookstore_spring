package com.belhard.bookstore.controller.error;

import com.belhard.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller("error")
public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/error/error.jsp";
    }
}
