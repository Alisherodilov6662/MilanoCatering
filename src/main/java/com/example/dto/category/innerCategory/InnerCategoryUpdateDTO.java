package com.example.dto.category.innerCategory;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class InnerCategoryUpdateDTO {
    private Long photo;
    private String nameUz;
    private String nameRu;
    private String descriptionUz;
    private String descriptionRu;
}
