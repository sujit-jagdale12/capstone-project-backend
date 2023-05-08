package com.ani.ems.dto;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEventTickeDto {
    private Long id;
    private String title;
    private LocalDate startdate;
    private LocalDate enddate;
    private String location;
    private LocalTime time;
    private String description;

}
