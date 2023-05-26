package com.ani.ems.dto;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewSpeakerDetails {

    @NotBlank(message = "speaker is mandatory")
    @NotNull(message = "speaker should not be null")
    private String speaker;

    @NotBlank(message = "topic is mandatory")
    @NotNull(message = "topic should not be null")
    private String topic;

    private LocalTime startTime;

    private LocalTime endTime;
}
