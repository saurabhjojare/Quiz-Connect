package com.galaxon.Quiz.Connect.service.interfaces;

import com.galaxon.Quiz.Connect.dto.response.ScoreResponse;
import com.galaxon.Quiz.Connect.model.Score;

import java.util.List;

public interface ScoreService {
    List<ScoreResponse> getScoresByUserId(Long userId);
    List<ScoreResponse> getAllScores();
    boolean deleteScoreById(Long scoreId);
    ScoreResponse updateScoreByUserId(Long userId);
}