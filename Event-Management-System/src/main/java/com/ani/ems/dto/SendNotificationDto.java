package com.ani.ems.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationDto {
    @NotBlank(message = "message is mandatory")
    @NotNull(message = "message should not be null")
    private String message;

}
