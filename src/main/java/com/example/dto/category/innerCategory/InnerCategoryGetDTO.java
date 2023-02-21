package com.example.dto.category.innerCategory;

import com.example.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InnerCategoryGetDTO {
    private Long id;
    private Long photo;
    private String nameRu;
    private String nameUz;
    private String descriptionRu;
    private String descriptionUz;
    private Status status;
    private LocalDateTime createdDate;
}
