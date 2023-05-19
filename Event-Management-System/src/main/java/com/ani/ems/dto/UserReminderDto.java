package com.ani.ems.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReminderDto {

    @NotBlank(message = "description is mandatory")
    @NotNull(message = "description should not be null")
    private String reminder;
}
