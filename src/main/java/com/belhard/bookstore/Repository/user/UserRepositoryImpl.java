package com.belhard.bookstore.Repository.user;

import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.dataMapper.DataMapper;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;
    private final DataMapper dataMapper;

    public User findByID(Long id) {
        return dataMapper.toEntity(userDao.read(id));
    }

    public User create(User user) {
        UserDto dto = dataMapper.userReadDto(user);
        UserDto created = userDao.create(dto);
        return  dataMapper.toEntity(created);
    }

    @Override
    public List<User> findAll() {
        return userDao.getAll().stream().map(dataMapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public User update(User user) {
        UserDto dto = dataMapper.userReadDto(user);
        UserDto updated = userDao.update(dto);
        return dataMapper.toEntity(updated);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}
