package com.belhard.bookstore.controller.book;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller("book")
public class BookCommand implements Command {
    @Autowired
    private BookService bookService;
    @Override
    public String execute(HttpServletRequest request) {
            if (bookService != null) {
                String bookId = request.getParameter("id");
                Long id = Long.parseLong(bookId);
                BookDto book = bookService.findById(id);
                request.setAttribute("book", book);
                return "jsp/book/book.jsp";
            }else {
                log.error("BookService is null");
                throw new RuntimeException("BookService is not available");
            }
    }
}
