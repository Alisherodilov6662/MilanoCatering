package com.example.dto.category.superCategory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Author: Alisher Odilov
 * Date: 21.02.2023
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryGetForPublish {
    private Long id;
    private String name_Uz;
    private String description_Uz;
}
