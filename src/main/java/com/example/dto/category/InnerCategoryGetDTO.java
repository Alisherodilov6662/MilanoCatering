package com.example.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InnerCategoryGetDTO {
    private Long photo;
    private Long category;
    private String nameRu;
    private String nameUz;
    private String descriptionRu;
    private String descriptionUz;
}
