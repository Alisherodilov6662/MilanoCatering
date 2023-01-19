package com.example.dto.category;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.entity.category.InnerCategoryEntity} entity
 */


@Data
public class InnerCategoryCreationDto {

    private Long photo;
    private Long category;
    private String nameUz;
    private String nameRu;
    private String descriptionUz;
    private String descriptionRu;
}