package com.project.fitnova.controller;

import com.project.fitnova.dto.AIRequest;
import com.project.fitnova.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final QnAService qnaService;

    @PostMapping("/ask")
    public ResponseEntity<String> askAI(@RequestBody AIRequest request) {
        return ResponseEntity.ok(qnaService.getAnswer(request.getQuestion()));
    }
}