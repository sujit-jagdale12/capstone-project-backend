package com.ani.ems.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    // @NotNull(message = "Must enter price")
    // @Max(value = 5000, message = "Price can't be more than 5000")
    // @Min(value = 100, message = "Price must be greater than 100")
    private Double price;

    // @NotNull(message = "Enter quantity")
    // @Min(value = 1, message = "Min Quantity must be greater than 1")
    private Integer maxquantity;

    @NotNull(message = "Ticket type can\'t be null")
    @NotBlank(message = "Must enter ticket type")
    private String type;
}
