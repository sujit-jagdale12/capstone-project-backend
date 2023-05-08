package com.ani.ems.dto;
import java.time.LocalDate;

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
    private String time;
    private String description;

}
