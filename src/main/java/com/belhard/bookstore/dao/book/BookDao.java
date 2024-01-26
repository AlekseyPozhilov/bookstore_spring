package com.belhard.bookstore.dao.book;

import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    BookDto create(BookDto book);

    BookDto update(BookDto book);

    boolean delete(Long id);

    List<BookDto> getAll();

    BookDto findById(Long id);

    BookDto findByIsbn(String isbn);

    long countAll();
}
