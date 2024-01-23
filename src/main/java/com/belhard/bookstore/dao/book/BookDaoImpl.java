package com.belhard.bookstore.dao.book;

import com.belhard.bookstore.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private static final String SELECT_ISBN_QUERY = "SELECT id, author, numberOfPages, price, yearOfPublishing, title FROM books WHERE isbn = ?";
    private static final String SELECT_ID_QUERY = "SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO books (author, isbn, numberOfPages, price, yearOfPublishing, title) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM books WHERE id = ?";
    private static final String SELECT_TABLE_QUERY = "SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books";
    private static final String UPDATE_NAMED_SCRIPT = "UPDATE books SET title = :title, author = :author, isbn = :isbn, yearOfPublishing = :yearOfPublishing, numberOfPages = :numberOfPages, price = :price WHERE id = :id";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Book create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getNumberOfPages());
            statement.setBigDecimal(4, book.getPrice());
            statement.setInt(5, book.getYearOfPublishing());
            statement.setString(6, book.getTitle());
            return statement;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        long id = (long) keys.get("id");
        return findById(id);
    }

    public Book update(Book book) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", book.getId());
        parameters.put("title", book.getTitle());
        parameters.put("author", book.getAuthor());
        parameters.put("isbn", book.getIsbn());
        parameters.put("yearOfPublishing", book.getYearOfPublishing());
        parameters.put("numberOfPages", book.getNumberOfPages());
        parameters.put("price", book.getPrice());
        namedParameterJdbcTemplate.update(UPDATE_NAMED_SCRIPT, parameters);
        return findById(book.getId());
    }

    public boolean delete(int id) {
        int rowsUpdated = jdbcTemplate.update(DELETE_QUERY, id);
        return rowsUpdated == 1;

    }

    public List<Book> getAll() {
        try {
            return jdbcTemplate.query(SELECT_TABLE_QUERY, this::mapRow);
        } catch (DataAccessException e) {
            return null;
        }

    }


    @Override
    public Book findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_ID_QUERY, this::mapRow, id);

    }

    public Book findByIsbn(String isbn) {
        return jdbcTemplate.queryForObject(SELECT_ISBN_QUERY, this::mapRow, isbn);

    }

    private Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setAuthor(resultSet.getString("author"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setNumberOfPages(resultSet.getInt("numberOfPages"));
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setYearOfPublishing(resultSet.getInt("yearOfPublishing"));
        book.setTitle(resultSet.getString("title"));
        return book;
    }

    public long countAll() {
        return getAll().size();
    }
}
