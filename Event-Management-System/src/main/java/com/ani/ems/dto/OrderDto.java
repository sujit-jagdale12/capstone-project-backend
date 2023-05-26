package com.ani.ems.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotBlank(message = "tickettype is mandatory")
    @NotNull(message = "tickettype should not be null")
    private String tickettype;

    @NotNull(message = "quantity should not be null")
    private Long quantity;
}
