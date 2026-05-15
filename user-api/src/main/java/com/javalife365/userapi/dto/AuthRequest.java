package com.javalife365.userapi.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
