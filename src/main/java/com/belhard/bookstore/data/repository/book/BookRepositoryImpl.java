package com.belhard.bookstore.data.repository.book;

import com.belhard.bookstore.data.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {
    public static final String FIND_BY_ISBN = "SELECT b FROM books b WHERE b.isbn = :isbn";
    public static final String GET_ALL = "from Book";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Book> findById(Long key) {
        return Optional.ofNullable(manager.find(Book.class, key));
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        TypedQuery<Book> query = manager.createQuery(FIND_BY_ISBN, Book.class);
        query.setParameter("isbn", isbn);

        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        return manager.createQuery(GET_ALL, Book.class).getResultList();
    }

    @Override
    public Book create(Book book) {
        if (book.getId() != null) {
            manager.merge(book);
        } else {
            manager.persist(book);
        }
        return book;
    }

    @Override
    public Book update(Book book) {
        if (book.getId() != null) {
            manager.merge(book);
            return manager.merge(book);
        }
        return null;
    }

    @Override
    public boolean delete(Long key) {
        Book book = manager.find(Book.class, key);
        if (book != null) {
            manager.remove(book);
            return true;
        }
        return false;
    }
}
