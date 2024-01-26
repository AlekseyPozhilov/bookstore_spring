package com.belhard.bookstore.controller.edit.user;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller("edit_user_form")
public class EditUserFormCommand implements Command {
    private final UserService userService;

    public String execute(HttpServletRequest req) {
        long id = Long.parseLong(req.getParameter("id"));
        UserDto toEdit = userService.findById(id);
        req.setAttribute("user", toEdit);
        return "jsp/user/editUserForm.jsp";
    }
}


