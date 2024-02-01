package com.belhard.bookstore.service.user;

import com.belhard.bookstore.data.repository.user.UserRepository;
import com.belhard.bookstore.data.dto.user.UserDto;
import com.belhard.bookstore.data.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserDto dto) {
        log.debug("Creating user: {}", dto);
        User user = mapToEntity(dto);
        User created = userRepository.create(user);
        log.debug("User created: {}", created);
        return mapToDto(created);
    }

    @Override
    public UserDto update(UserDto dto) {
        log.debug("Updating user: {}", dto);

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("No user with id = " + dto.getId()));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());

        User edited = userRepository.update(user);

        log.debug("User updated: {}", edited);

        return mapToDto(edited);
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting user: {}", id);

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(id);
        } else {
            throw new RuntimeException("No user with id = " + id);
        }

        log.debug("User deleted: {}", id);
    }

    @Override
    public UserDto findByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found"));

        log.debug("User received: {}", email);

        return mapToDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user with id = " + id));
        return mapToDto(user);
    }

    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setGender(user.getGender());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setPassword(user.getPassword());
        return dto;
    }

    private User mapToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        return user;
    }
}
