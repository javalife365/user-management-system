package com.javalife365.userapi.dto;

import com.javalife365.userapi.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
