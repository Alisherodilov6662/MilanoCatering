package com.example.dto.category.superCategory;

import com.example.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.entity.category.CategoryEntity} entity
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryGetDTO(Long id,
                             Long photoId,
                             String nameUz,
                             String nameRu,
                             String descriptionUz,
                             String descriptionRu,
                             LocalDateTime createdDate,
                             LocalDateTime updatedDate,
                             Status status) {


}