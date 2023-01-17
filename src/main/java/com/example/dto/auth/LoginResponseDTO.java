package com.example.dto.auth;

import com.example.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDTO {
    private String name;
    private String surname;
    private String username;
    private ProfileRole role;
    private String token;
}
