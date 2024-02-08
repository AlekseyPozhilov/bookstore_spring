package com.belhard.bookstore.service.book;

import com.belhard.bookstore.data.repository.book.BookRepository;
import com.belhard.bookstore.data.dto.book.BookDto;
import com.belhard.bookstore.data.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No book with id = " + id));
        return mapToDto(book);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        log.debug("Fetching book by email: {}", isbn);
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book with isbn " + isbn + " not found"));

        log.debug("Book received: {}", isbn);

        return mapToDto(book);
    }

    @Override
    public BookDto create(BookDto dto) {
        log.debug("Creating book: {}", dto);
        Book book = mapToEntity(dto);
        Book created = bookRepository.create(book);
        log.debug("Book created: {}", created);
        return mapToDto(created);
    }

    @Override
    public BookDto update(BookDto dto) {
        log.debug("Updating book: {}", dto);

        Book book = bookRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("No book with id = " + dto.getId()));

        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setPrice(dto.getPrice());
        book.setYearOfPublishing(dto.getYearOfPublishing());
        book.setTitle(dto.getTitle());

        Book edited = bookRepository.update(book);

        log.debug("Book updated: {}", edited);

        return mapToDto(edited);
    }

@Override
public void delete(Long id) {
    log.debug("Deleting book: {}", id);

    if (!bookRepository.delete(id)) {
        throw new RuntimeException("No user with id = " + id);
    }

    log.debug("Book deleted: {}", id);
}

    private BookDto mapToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setNumberOfPages(book.getNumberOfPages());
        dto.setPrice(book.getPrice());
        dto.setYearOfPublishing(book.getYearOfPublishing());
        dto.setTitle(book.getTitle());
        return dto;
    }

    private Book mapToEntity(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setPrice(dto.getPrice());
        book.setYearOfPublishing(dto.getYearOfPublishing());
        book.setTitle(dto.getTitle());
        return book;
    }
}
