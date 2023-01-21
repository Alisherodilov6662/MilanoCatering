package com.example.dto.category.superCategory;

import com.example.entity.category.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * A DTO for the {@link CategoryEntity} entity
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreationDto{

    @NotNull
    private  Long  photo;
    @Length(min = 2,message = "name is not valid !")
    private  String nameUz;
    @Length(min = 2,message = "name is not valid !")
    private  String nameRu;
    @Length(min = 2,message = "name is not valid !")
    private  String descriptionUz;
    @Length(min = 2,message = "name is not valid !")
    private  String descriptionRu;
}