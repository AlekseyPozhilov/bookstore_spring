package com.belhard.bookstore.web.controller.book;

import com.belhard.bookstore.data.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
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
@RequestMapping("/books")
public class BookCommand {
    private final BookService bookService;
    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/getAll")
    public String getBooks(Model model) {
        List<BookDto> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/create")
    public String createBookForm() {
        return "createBookForm";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDto dto) {
        BookDto bookDto = bookService.create(dto);
        return "redirect:/books/" + bookDto.getId();
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("book", book);
        return "editBookForm";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@ModelAttribute BookDto dto) {
        bookService.update(dto);
        return "redirect:/books/" + dto.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteBookForm(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books/getAll";
    }
}
