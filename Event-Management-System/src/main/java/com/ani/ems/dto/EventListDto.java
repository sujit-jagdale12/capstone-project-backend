package com.ani.ems.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class EventListDto {
    private Long id;
    private String title;
    private LocalDate startdate;
    private LocalDate enddate;
    private String location;
    private LocalTime time;
    
}
