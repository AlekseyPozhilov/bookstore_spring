package com.belhard.bookstore.Repository.user;

import com.belhard.bookstore.entity.User;

import java.util.List;

public interface UserRepository {
    User findByID(Long id);
    User create(User user);
    List<User> findAll();
    User update(User user);
    boolean delete(Long id);
}
