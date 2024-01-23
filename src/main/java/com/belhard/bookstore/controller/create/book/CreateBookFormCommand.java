package com.belhard.bookstore.controller.create.book;

import com.belhard.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("create_book_form")
public class CreateBookFormCommand implements Command {
    public String execute(HttpServletRequest req) {
        return "jsp/book/createBookForm.jsp";
    }
}
