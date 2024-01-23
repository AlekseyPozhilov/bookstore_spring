package com.belhard.bookstore.dao.book;

import com.belhard.bookstore.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    Book create(Book book) throws SQLException;

    Book update(Book book) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<Book> getAll() throws SQLException;

    Book findById(Long id);

    Book findByIsbn(String isbn) throws SQLException;

    long countAll() throws SQLException;
}
