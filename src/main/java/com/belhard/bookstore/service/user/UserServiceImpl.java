package com.belhard.bookstore.service.user;

import com.belhard.bookstore.Repository.user.UserRepository;
import com.belhard.bookstore.dto.user.CreateUserDto;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserServiceImpl::userReadDto).toList();
    }

    @Override
    public UserDto create(CreateUserDto dto) {
        log.debug("Creating user: {}", dto);
        User user = toEntityCreate(dto);
        User created = userRepository.create(user);
        log.debug("User created: {}", user);
        return userReadDto(created);
    }

    @Override
    public UserDto update(UserDto dto) {
        log.debug("Updating user: {}", dto);

        UserDto userDto = findById(dto.getId());
        userDto.setFirstName(dto.getFirstName());
        userDto.setLastName(dto.getLastName());
        userDto.setEmail(dto.getEmail());
        userDto.setDateOfBirth(dto.getDateOfBirth());
        userDto.setGender(dto.getGender());
        userDto.setPhoneNumber(dto.getPhoneNumber());
        userDto.setPassword(dto.getPassword());

        User user = toEntity(userDto);
        User edited = userRepository.update(user);

        log.debug("User updated: {}", userDto);

        return userReadDto(edited);
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting user: {}", id);

        userRepository.delete(id);

        log.debug("User deleted: {}", id);
    }

    @Override
    public UserDto findByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User with email " + email + " not found");
        }

        log.debug("User received: {}", email);

        return userReadDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findByID(id);
        if (user == null) {
            throw new RuntimeException("No user with id = " + id);
        }
        return userReadDto(user);
    }

    private static UserDto userReadDto(User userEntity) {
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

    private static User toEntityCreate(CreateUserDto dto) {
        User userEntity = new User();
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setDateOfBirth(dto.getDateOfBirth());
        userEntity.setGender(dto.getGender());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setPassword(dto.getPassword());
        return userEntity;
    }

    private static User toEntity(UserDto dto) {
        User userEntity = new User();
        userEntity.setId(dto.getId());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setDateOfBirth(dto.getDateOfBirth());
        userEntity.setGender(dto.getGender());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setPassword(dto.getPassword());
        return userEntity;
    }
}
