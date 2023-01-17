package com.example.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String role;
}
