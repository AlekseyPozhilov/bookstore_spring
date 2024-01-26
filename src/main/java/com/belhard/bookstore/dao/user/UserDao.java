package com.belhard.bookstore.dao.user;

import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    UserDto create(UserDto user);

    UserDto read(Long id);

    UserDto update(UserDto user);

    boolean delete(Long id) ;

    UserDto findByEmail(String email);

    List<UserDto> getAll();
}
