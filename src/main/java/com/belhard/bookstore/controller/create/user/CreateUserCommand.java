package com.belhard.bookstore.controller.create.user;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.controller.FrontController;
import com.belhard.bookstore.dto.user.CreateUserDto;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Log4j2
@RequiredArgsConstructor
@Controller("create_user")
public class CreateUserCommand implements Command {
    private final UserService userService;
    public String execute(HttpServletRequest req) {
        UserDto toSave = processRequest(req);
        UserDto saved = userService.create(toSave);

        return FrontController.REDIRECT + FrontController.PATH + "user&id=" + saved.getId();
    }

    private static UserDto processRequest(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDto dto = new UserDto();
        dto.setFirstName(firstName);
        dto.setEmail(email);
        dto.setPassword(password);

        return dto;
    }

}
