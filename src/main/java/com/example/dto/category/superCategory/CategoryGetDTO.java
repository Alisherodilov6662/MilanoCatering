package com.example.dto.category.superCategory;

import com.example.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.entity.category.CategoryEntity} entity
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryGetDTO {

    private  Long id;
    private  Long photoId;
    private  String nameUz;
    private  String nameRu;
    private  String descriptionUz;
    private  String descriptionRu;
    private  LocalDateTime createdDate;
    private  LocalDateTime updatedDate;
    private Status status;
}