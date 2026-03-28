package com.project.fitnova.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.fitnova.model.ActivityType;
import com.project.fitnova.model.Recommendations;
import com.project.fitnova.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
