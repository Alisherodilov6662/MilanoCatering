package com.example.entity.category;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link CategoryEntity} entity
 */

@Data
public class CategoryCreationDto implements Serializable {

    private  Long  photo;
    private  String nameUz;
    private  String nameRu;
    private  String descriptionUz;
    private  String descriptionRu;
}