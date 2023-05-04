package com.ani.ems.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @NotNull(message = "Enter Ticket Price")
    // @Max(value = 5000, message = "Price can't be more than 5000")
    // @Min(value = 100, message = "Price must be greater than 100")
    private Double price;

    // @NotNull(message = "Enter max Ticket Quantity")
    // @Min(value = 1, message = "Min Quantity must be greater than 1")
    private Integer maxquantity;

    @NotBlank(message = "Enter Ticket type")
    @NotNull(message = "Enter Ticket Type")
    private String type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
