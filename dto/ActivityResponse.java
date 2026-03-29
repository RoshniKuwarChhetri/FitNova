package com.project.fitnova.dto;
import com.project.fitnova.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {

    private String id;
    private String userId;
    private ActivityType type;
    private Map<String, Object> additionalMatrices;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
