package com.belhard.bookstore.service.book;

import com.belhard.bookstore.data.dto.book.BookDto;

import java.util.List;
public interface BookService {
    	List<BookDto> findAll();
    	BookDto findById(Long id);
		BookDto findByIsbn(String isbn);
    	BookDto create(BookDto dto);
    	BookDto update(BookDto dto);
    	void delete(Long id);
}
