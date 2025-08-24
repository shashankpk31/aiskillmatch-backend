package com.shashankpk.AISkillMatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shashankpk.AISkillMatch.service.AIService;

@RestController
@RequestMapping("/api/ai")
public class AIController {
    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/advice")
    @PreAuthorize("hasAuthority('JOBSEEKER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> getAdvice(@RequestBody String prompt) {
        return ResponseEntity.ok(aiService.getAIAdvice(prompt));
    }
}
