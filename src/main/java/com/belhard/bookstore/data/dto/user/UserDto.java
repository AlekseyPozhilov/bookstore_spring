package com.belhard.bookstore.data.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String password;
}
