package com.belhard.bookstore.Repository.book;

import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.dataMapper.DataMapper;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final BookDao bookDao;
    private final DataMapper dataMapper;

    @Override
    public Book findById(Long id) {
        return dataMapper.toEntity(bookDao.findById(id));
    }

    @Override
    public Book findByIsbn(String isbn) {
        return dataMapper.toEntity(bookDao.findByIsbn(isbn));
    }

    @Override
    public List<Book> findAll() {
        return bookDao.getAll().stream().map(dataMapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public Book create(Book book){
        BookDto dto = dataMapper.bookReadDto(book);
        BookDto created = bookDao.create(dto);
        return  dataMapper.toEntity(created);
    }
    @Override
    public Book update(Book book){
        BookDto dto = dataMapper.bookReadDto(book);
        BookDto updated = bookDao.update(dto);
        return dataMapper.toEntity(updated);
    }
    @Override
    public boolean delete(Long id){
        return bookDao.delete(id);
    }
}
