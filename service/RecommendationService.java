package com.project.fitnova.service;

import com.project.fitnova.dto.RecommendationRequest;
import com.project.fitnova.model.Activity;
import com.project.fitnova.model.Recommendation;
import com.project.fitnova.model.User;
import com.project.fitnova.repository.ActivityRepository;
import com.project.fitnova.repository.RecommendationRepository;
import com.project.fitnova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final QnAService qnaService;

    public Recommendation generateRecommendation(RecommendationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found: " + request.getActivityId()));

        String aiResponse = qnaService.getAnswer(
                "Give fitness recommendation for activity type " + activity.getType()
        );

        if (aiResponse == null || aiResponse.isBlank()) {
            aiResponse = "No recommendation available currently.";
        }

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .recommendation(aiResponse)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        return recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}