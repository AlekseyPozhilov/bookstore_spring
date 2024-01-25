package com.belhard.bookstore.service.book;

import com.belhard.bookstore.Repository.book.BookRepository;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.book.CreateBookDto;
import com.belhard.bookstore.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookDto> findAll() {
        log.debug("Fetching all books");

        return bookRepository.findAll().stream().map(BookServiceImpl::bookReadDto).toList();
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Fetching book by ID: {}", id);

        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new RuntimeException("No book with id = " + id);
        }
        return bookReadDto(book);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        log.debug("Fetching book by ISBN: {}", isbn);
        Book book = bookRepository.findByIsbn(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book with isbn " + isbn + " not found");
        }

        log.debug("Book received: {}", isbn);

        return bookReadDto(book);
    }

    @Override
    public BookDto create(CreateBookDto dto) {
        log.debug("Creating book: {}", dto);

        Book book = toEntityCreate(dto);
        Book created = bookRepository.create(book);
        log.debug("Book created: {}", book);
        return bookReadDto(created);
    }

    @Override
    public BookDto update(BookDto dto) {
        log.debug("Updating book: {}", dto);
        BookDto bookDto = findById(dto.getId());
        bookDto.setAuthor(dto.getAuthor());
        bookDto.setIsbn(dto.getIsbn());
        bookDto.setNumberOfPages(dto.getNumberOfPages());
        bookDto.setPrice(dto.getPrice());
        bookDto.setYearOfPublishing(dto.getYearOfPublishing());
        bookDto.setTitle(dto.getTitle());

        Book book = toEntity(bookDto);
        Book edited = bookRepository.update(book);

        log.debug("Book updated: {}", bookDto);

        return bookReadDto(edited);
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting book: {}", id);

        bookRepository.delete(id);

        log.debug("Book deleted: {}", id);
    }

    private static BookDto bookReadDto(Book bookEntity) {
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

    private static Book toEntity(BookDto dto) {
        Book bookEntity = new Book();
        bookEntity.setId(dto.getId());
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setIsbn(dto.getIsbn());
        bookEntity.setNumberOfPages(dto.getNumberOfPages());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
        bookEntity.setTitle(dto.getTitle());
        return bookEntity;
    }

    private static Book toEntityCreate(CreateBookDto dto) {
        Book bookEntity = new Book();
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setIsbn(dto.getIsbn());
        bookEntity.setNumberOfPages(dto.getNumberOfPages());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
        bookEntity.setTitle(dto.getTitle());
        return bookEntity;
    }
}
