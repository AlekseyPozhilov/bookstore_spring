package com.belhard.bookstore.service.user;

import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.dto.user.CreateUserDto;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> findAll() {
        log.debug("Fetching all users");
        List<UserDto> users = userDao.getAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (UserDto user : users) {
            UserDto userDto = userReadDto(user);

            userDtos.add(userDto);
        }

        log.debug("All users received");

        return userDtos;
    }

    @Override
    public UserDto create(CreateUserDto dto) {
        log.debug("Creating user: {}", dto);
        UserDto user = toEntity(dto);
        UserDto created = userDao.create(user);

        log.debug("User created: {}", user);

        return userReadDto(created);
    }

    private static UserDto toEntity(CreateUserDto dto) {
        UserDto userEntity = new UserDto();
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setDateOfBirth(dto.getDateOfBirth());
        userEntity.setGender(dto.getGender());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setPassword(dto.getPassword());
        return userEntity;
    }

    @Override
    public UserDto update(UserDto dto) {
        log.debug("Updating user: {}", dto);
        UserDto userEntity = new UserDto();
        userEntity.setId(dto.getId());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setDateOfBirth(dto.getDateOfBirth());
        userEntity.setGender(dto.getGender());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setPassword(dto.getPassword());

        userDao.update(userEntity);

        log.debug("User updated: {}", userEntity);

        return dto;
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting user: {}", id);

        userDao.delete(id);

        log.debug("User deleted: {}", id);
    }

    @Override
    public UserDto findByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        UserDto userEntity = userDao.findByEmail(email);

        if (userEntity == null) {
            throw new IllegalArgumentException("User with email " + email + " not found");
        }

        UserDto dto = userReadDto(userEntity);

        log.debug("User received: {}", dto);

        return dto;
    }

    private static UserDto userReadDto(UserDto userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setEmail(userEntity.getEmail());
        dto.setDateOfBirth(userEntity.getDateOfBirth());
        dto.setGender(userEntity.getGender());
        dto.setPhoneNumber(userEntity.getPhoneNumber());
        dto.setPassword(userEntity.getPassword());
        return dto;
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("Fetching user by ID: {}", id);

        UserDto userEntity = userDao.read(id);
        UserDto dto = userReadDto(userEntity);

        log.debug("User received", dto);
        return dto;
    }
}
