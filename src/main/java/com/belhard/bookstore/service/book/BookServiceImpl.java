package com.belhard.bookstore.service.book;

import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.book.CreateBookDto;
import com.belhard.bookstore.entity.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> findAll() {
        log.debug("Fetching all books");

        List<BookDto> bookEntities = bookDao.getAll();
        List<BookDto> dtos = new ArrayList<>();
        for (BookDto bookEntity : bookEntities) {
            BookDto dto = new BookDto();
            dto.setId(bookEntity.getId());
            dto.setAuthor(bookEntity.getAuthor());
            dto.setIsbn(bookEntity.getIsbn());
            dto.setNumberOfPages(bookEntity.getNumberOfPages());
            dto.setPrice(bookEntity.getPrice());
            dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
            dto.setTitle(bookEntity.getTitle());
            dtos.add(dto);
        }

        log.debug("All books received");

        return dtos;
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Fetching book by ID: {}", id);

        BookDto bookEntity = bookDao.findById(id);
        if (bookEntity == null) {
            log.error("Error");
            throw new RuntimeException("No user with id:" + id);
        }
        return bookReadDto(bookEntity);
    }

    private static BookDto bookReadDto(BookDto bookEntity) {
        BookDto dto = new BookDto();
        dto.setId(bookEntity.getId());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setNumberOfPages(bookEntity.getNumberOfPages());
        dto.setPrice(bookEntity.getPrice());
        dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
        dto.setTitle(bookEntity.getTitle());

        return dto;
    }

    public BookDto findByIsbn(String isbn) {
        log.debug("Fetching book by ISBN: {}", isbn);
        BookDto bookEntity = bookDao.findByIsbn(isbn);
        BookDto dto = new BookDto();
        dto.setId(bookEntity.getId());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setNumberOfPages(bookEntity.getNumberOfPages());
        dto.setPrice(bookEntity.getPrice());
        dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
        dto.setTitle(bookEntity.getTitle());

        log.debug("Book received: {}", dto);

        return dto;
    }

    @Override
    public BookDto create(CreateBookDto dto) {
        log.debug("Creating book: {}", dto);

        BookDto book = toEntity(dto);
        BookDto created = bookDao.create(book);

        log.debug("book created: {}", book);

        return bookReadDto(created);
    }

    private static BookDto toEntity(CreateBookDto dto) {
        BookDto bookEntity = new BookDto();
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setIsbn(dto.getIsbn());
        bookEntity.setNumberOfPages(dto.getNumberOfPages());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
        bookEntity.setTitle(dto.getTitle());
        return bookEntity;
    }

    @Override
    public BookDto update(BookDto dto) {
        log.debug("Updating book: {}", dto);
        BookDto bookEntity = new BookDto();
        bookEntity.setId(dto.getId());
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setIsbn(dto.getIsbn());
        bookEntity.setNumberOfPages(dto.getNumberOfPages());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
        bookEntity.setTitle(dto.getTitle());

        bookDao.update(bookEntity);

        log.debug("Book updated: {}", bookEntity);

        return dto;
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting book: {}", id);

        bookDao.delete(id);

        log.debug("Book deleted: {}", id);
    }
}
