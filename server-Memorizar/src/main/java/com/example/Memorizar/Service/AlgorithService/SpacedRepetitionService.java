package com.example.Memorizar.Service.AlgorithService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpacedRepetitionService {
    public Map<String, LocalDateTime> calculateNextReviewDates(LocalDateTime lastReviewDate) {
        
        Map<String, LocalDateTime> reviewOptions = new HashMap<>();
        reviewOptions.put("Again", lastReviewDate.plusMinutes(1));
        reviewOptions.put("Hard", lastReviewDate.plusMinutes(10));
        reviewOptions.put("Good", lastReviewDate.plusDays(1));
        reviewOptions.put("Easy", lastReviewDate.plusDays(2));

        return reviewOptions;
    }

}
