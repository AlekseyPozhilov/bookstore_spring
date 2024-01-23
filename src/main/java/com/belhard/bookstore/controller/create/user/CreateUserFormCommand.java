package com.belhard.bookstore.controller.create.user;

import com.belhard.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("create_user_form")
public class CreateUserFormCommand implements Command {
    public String execute(HttpServletRequest req) {
        return "jsp/user/createUserForm.jsp";
    }

}
