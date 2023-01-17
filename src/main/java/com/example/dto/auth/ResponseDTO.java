package com.example.dto.auth;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private Integer id;
    private String name;
    private String username;
    private ProfileRole role;
}
