package com.example.dto.category.innerCategory;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InnerCategoryUpdateDTO {
    private Long photo;
    private String nameUz;
    private String nameRu;
    private String descriptionUz;
    private String descriptionRu;
}
