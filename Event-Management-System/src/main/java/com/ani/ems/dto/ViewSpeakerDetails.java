package com.ani.ems.dto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
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