package com.example.dto;

import com.example.validation.ValidPhone;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    @Length(min = 2, message = "Name has to 2 letters at least !")
    private String name;
    @Length(min=2, message = "Message is not valid ! ")
    private String message;
    @ValidPhone(message = "Phone is not valid !")
    private String phone;

}
