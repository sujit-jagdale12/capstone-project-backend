package com.ani.ems.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotpassDto {

    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email should not be null")
    private String email;

}
