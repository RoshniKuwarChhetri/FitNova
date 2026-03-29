package com.project.fitnova.dto;

import com.project.fitnova.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Map<String, Object> additionalMatrices;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startedAt;

}
