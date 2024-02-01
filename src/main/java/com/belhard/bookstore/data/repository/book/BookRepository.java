package com.belhard.bookstore.data.repository.book;

import com.belhard.bookstore.data.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(Long id);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findAll();
    Book create(Book book);
    Book update(Book book);
    boolean delete(Long id);
}
