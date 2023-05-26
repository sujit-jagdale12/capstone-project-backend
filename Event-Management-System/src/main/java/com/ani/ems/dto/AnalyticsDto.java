package com.ani.ems.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AnalyticsDto {

    private String ticketType;
    private Long ticketCount;
    private Long totalQuantity;
}
