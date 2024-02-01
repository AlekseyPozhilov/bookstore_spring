package com.belhard.bookstore.web.controller.user;

import com.belhard.bookstore.data.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserCommand {
    private final UserService userService;

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        UserDto user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("No user with id = " + id);
        }
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/getAll")
    public String getUsers(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String createUserForm() {
        return "createUserForm";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto dto) {
        UserDto userDto = userService.create(dto);
        return "redirect:/users/" + userDto.getId();
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        UserDto user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("No user with id = " + id);
        }
        model.addAttribute("user", user);
        return "editUserForm";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute UserDto dto) {
        userService.update(dto);
        return "redirect:/users/" + dto.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users/getAll";
    }
}
