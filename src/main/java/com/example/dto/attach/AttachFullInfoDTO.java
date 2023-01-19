package com.example.dto.attach;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttachFullInfoDTO {

    private Long id;
    private String name;
    private LocalDateTime createdDate;
}
