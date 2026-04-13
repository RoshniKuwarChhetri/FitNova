package com.project.fitnova.service;

import com.project.fitnova.dto.ActivityRequest;
import com.project.fitnova.dto.ActivityResponse;
import com.project.fitnova.model.Activity;
import com.project.fitnova.model.User;
import com.project.fitnova.repository.ActivityRepository;
import com.project.fitnova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    // ✅ Track Activity
    public ActivityResponse trackActivity(ActivityRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user: " + request.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startedAt(request.getStartedAt())
                .additionalMetrics(request.getAdditionalMetrics()) // FIXED NAME
                .build();

        Activity savedActivity = activityRepository.save(activity);

        return mapToResponse(savedActivity);
    }

    // ✅ Convert Entity → DTO
    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();

        response.setId(activity.getId());
        response.setUserId(activity.getUser().getId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());

        response.setCreatedAt(activity.getCreatedAt()); // FIXED
        response.setUpdatedAt(activity.getUpdatedAt());

        return response;
    }

    // ✅ Get Activities
    public List<ActivityResponse> getUserActivities(String userId) {

        List<Activity> activityList = activityRepository.findByUserId(userId);

        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}