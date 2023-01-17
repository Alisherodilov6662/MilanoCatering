package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Long chatId;
    private ProfileRole role;
    private LocalDateTime createdDate = LocalDateTime.now();
}
