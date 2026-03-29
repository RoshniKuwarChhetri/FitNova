package com.project.fitnova.service;

import com.project.fitnova.dto.ActivityRequest;
import com.project.fitnova.dto.ActivityResponse;
import com.project.fitnova.model.User;
import com.project.fitnova.repository.ActivityRepository;
import com.project.fitnova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import com.project.fitnova.model.Activity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
  private final UserRepository userRepository;


    public  ActivityResponse trackActivity(ActivityRequest request) {
        User user=userRepository.findById(request.getUserId()).orElseThrow(()->new RuntimeException("Invalid user: " +request.getUserId()));
        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startedAt(request.getStartedAt())
                .additionalMatrices(request.getAdditionalMatrices())
                .build();
        Activity saveActivity=activityRepository.save(activity);

      return mapToResponse(saveActivity);

    }

    private ActivityResponse mapToResponse(Activity saveActivity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(saveActivity.getId());
        response.setUserId(saveActivity.getUser().getId());
        response.setType(saveActivity.getType());
        response.setDuration(saveActivity.getDuration());
        response.setCaloriesBurned(saveActivity.getCaloriesBurned());
        response.setAdditionalMatrices(saveActivity.getAdditionalMatrices());
        response.setUpdatedAt(saveActivity.getCreatedAt());
        response.setUpdatedAt(saveActivity.getUpdatedAt());
        return response;
    }
     @GetMapping
    public  List<ActivityResponse> getUserActivities(String userId) {
        List<Activity>  activityList = activityRepository.findByUserId(userId);
//        1. Activity--> ActivityResponse
//        2.--> collect in list and return
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
