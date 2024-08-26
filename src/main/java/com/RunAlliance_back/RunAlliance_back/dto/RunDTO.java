package com.RunAlliance_back.RunAlliance_back.dto;

import com.RunAlliance_back.RunAlliance_back.model.DifficultyLevel;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RunDTO {
    private Long runId;
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private DifficultyLevel difficultyLevel;
    private Double distanceKm;
    private Integer durationMinutes;
}
