package com.belhard.bookstore.controller.edit.user;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.controller.FrontController;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller("edit_user")
public class EditUserCommand implements Command {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        UserDto toEdit = processRequest(req);
        UserDto edited = userService.update(toEdit);

        return FrontController.REDIRECT + FrontController.PATH + "user&id=" + edited.getId();
    }

    private static UserDto processRequest(HttpServletRequest req){
        long id = Long.parseLong(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setEmail(email);
        userDto.setPassword(password);
        return userDto;
    }
}
