package com.belhard.bookstore.controller.edit.book;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller("edit_book_form")
public class EditBookFormCommand implements Command {
    private final BookService bookService;
    public String execute(HttpServletRequest req) {
        long id = Long.parseLong(req.getParameter("id"));
        BookDto toEdit = bookService.findById(id);
        req.setAttribute("book", toEdit);
        return "jsp/book/editBookForm.jsp";
    }
}
