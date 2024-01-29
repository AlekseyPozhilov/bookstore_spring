package com.belhard.bookstore.repository.user;

import com.belhard.bookstore.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long key);
    User create(User user);

    List<User> findAll();
    User update(User user);

    boolean delete(Long id);

    Optional<User> findByEmail(String email);
}
