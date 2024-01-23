package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Log4j2
@Controller("user")
public class UserCommand implements Command {
    @Autowired
    private UserService userService;
    @Override
    public String execute(HttpServletRequest request) {
        if (userService != null) {
            String userId = request.getParameter("id");
            Long id = Long.parseLong(userId);
            UserDto user = userService.findById(id);
            request.setAttribute("user", user);
            return "jsp/user/user.jsp";
        } else {
            log.error("UserService is null");
            throw new RuntimeException("UserService is not available");
        }

    }
}
