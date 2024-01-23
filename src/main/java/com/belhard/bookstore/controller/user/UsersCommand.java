package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Log4j2
@Controller("users")
public class UsersCommand implements Command {
    @Autowired
    private UserService userService;

    @Override
    public String execute(HttpServletRequest request) {
        if (userService != null) {
            List<UserDto> users = userService.findAll();
            request.setAttribute("users", users);
            return "jsp/user/users.jsp";
        } else {
            log.error("UserService is null");
            throw new RuntimeException("UserService is not available");
        }

    }
}
