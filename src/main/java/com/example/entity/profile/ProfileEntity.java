package com.example.entity.profile;

import com.example.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private Long chatId;
    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @Column
    private LocalDateTime createdDate = LocalDateTime.now();
}
