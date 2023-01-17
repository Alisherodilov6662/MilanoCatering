package com.example.dto.jwt;

import com.example.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    private String username;
    private ProfileRole role;
}
