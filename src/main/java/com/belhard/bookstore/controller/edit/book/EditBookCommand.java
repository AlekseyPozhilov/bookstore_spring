package com.belhard.bookstore.controller.edit.book;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.controller.FrontController;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Log4j2
@RequiredArgsConstructor
@Controller("edit_book")
public class EditBookCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        BookDto toEdit = processRequest(req);
        BookDto edited = bookService.update(toEdit);

        return FrontController.REDIRECT + FrontController.PATH + "book&id=" + edited.getId();
    }

    private static BookDto processRequest(HttpServletRequest req){
        long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String priceString = req.getParameter("price");
        Integer numberOfPages = Integer.valueOf(req.getParameter("numberOfPages"));
        Integer yearOfPublishing = Integer.valueOf(req.getParameter("yearOfPublishing"));
        BigDecimal price = new BigDecimal(priceString);
        BookDto bookDto = new BookDto();
        bookDto.setId(id);
        bookDto.setTitle(title);
        bookDto.setAuthor(author);
        bookDto.setPrice(price);
        bookDto.setNumberOfPages(numberOfPages);
        bookDto.setYearOfPublishing(yearOfPublishing);
        return bookDto;
    }
}

