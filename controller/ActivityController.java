package com.project.fitnova.controller;

import com.project.fitnova.dto.ActivityRequest;
import com.project.fitnova.dto.ActivityResponse;
import com.project.fitnova.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {


    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> getUserActivity(@RequestBody ActivityRequest activityRequest){
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivity(@RequestHeader(value="X-User-ID") String userId){

        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }


}
