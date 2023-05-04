package com.ani.ems.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EventListDto {
    private Long id;
    private String title;
    private LocalDate startdate;
    private LocalDate enddate;
    private String location;
    private String time;
}
