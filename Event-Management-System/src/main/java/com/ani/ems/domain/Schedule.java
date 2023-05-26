package com.ani.ems.domain;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "speaker is mandatory")
    @NotNull(message = "speaker should not be null")
    private String speaker;

    @NotBlank(message = "topic is mandatory")
    @NotNull(message = "topic should not be null")
    private String topic;

    private LocalTime startTime;

    private LocalTime endTime;

    @NotBlank(message = "vendor is mandatory")
    @NotNull(message = "vendor should not be null")
    private String vendor;

    @NotBlank(message = "service details is mandatory")
    @NotNull(message = "service details should not be null")
    private String service;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
