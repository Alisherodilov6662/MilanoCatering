package com.example.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.entity.category.InnerCategoryEntity} entity
 */


@Data
public class InnerCategoryCreationDto {
    @NotNull(message = "photo id is required")
    private Long photo;
    @NotNull
    private Long category;
    @Length(min = 2,message = "Length must be logger than 2")
    private String nameUz;
    @Length(min = 2,message = "Length must be longer than 2")
    private String nameRu;
    @Length(min = 20,message = "Length must be longer then 20")
    private String descriptionUz;
    @Length(min = 20,message = "Length must be longer then 20")
    private String descriptionRu;
    private Boolean visible;
}