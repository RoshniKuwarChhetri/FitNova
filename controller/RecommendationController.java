package com.project.fitnova.controller;

import com.project.fitnova.dto.RecommendationRequest;
import com.project.fitnova.model.Recommendation;
import com.project.fitnova.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    // Generate Recommendation
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
            @RequestBody RecommendationRequest request) {

        Recommendation recommendation = recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    // Get by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    // Get by Activity
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getByActivity(@PathVariable String activityId) {
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}