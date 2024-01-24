package com.belhard.bookstore.Repository.book;

import com.belhard.bookstore.entity.Book;

import java.util.List;

public interface BookRepository {
    Book findById(Long id);
    Book findByIsbn(String isbn);
    List<Book> findAll();
    Book create(Book book);
    Book update(Book book);
    boolean delete(Long id);
}
