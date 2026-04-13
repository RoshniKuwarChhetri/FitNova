package com.project.fitnova.controller;

import com.project.fitnova.dto.ActivityRequest;
import com.project.fitnova.dto.ActivityResponse;
import com.project.fitnova.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // Track Activity
    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request) {
        ActivityResponse response = activityService.trackActivity(request);
        return ResponseEntity.ok(response);
    }

    // Get Activities
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(
            @RequestHeader("X-User-ID") String userId) {

        List<ActivityResponse> activities = activityService.getUserActivities(userId);
        return ResponseEntity.ok(activities);
    }
}