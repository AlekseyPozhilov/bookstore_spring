package com.belhard.bookstore.controller.create.book;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.controller.FrontController;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.book.CreateBookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Controller("create_book")
public class CreateBookCommand implements Command {
    private final BookService bookService;
    public String execute(HttpServletRequest req) {
        CreateBookDto toSave = processRequest(req);
        BookDto saved = bookService.create(toSave);

        return FrontController.REDIRECT + FrontController.PATH + "book&id=" + saved.getId();
    }

    private static CreateBookDto processRequest(HttpServletRequest req) {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String isbn = req.getParameter("isbn");
        String priceString = req.getParameter("price");
        BigDecimal price = new BigDecimal(priceString);
        Integer numberOfPages = Integer.valueOf(req.getParameter("numberOfPages"));
        Integer yearOfPublishing = Integer.valueOf(req.getParameter("yearOfPublishing"));

        CreateBookDto dto = new CreateBookDto();
        dto.setTitle(title);
        dto.setAuthor(author);
        dto.setIsbn(isbn);
        dto.setPrice(price);
        dto.setNumberOfPages(numberOfPages);
        dto.setYearOfPublishing(yearOfPublishing);

        return dto;
    }
}
