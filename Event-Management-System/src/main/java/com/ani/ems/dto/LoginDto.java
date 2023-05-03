package com.ani.ems.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email should not be null")
    private String email;

    @NotNull(message = "Password should not be null")
    @NotBlank(message = "Password is mandatory")
    private String password;
}
