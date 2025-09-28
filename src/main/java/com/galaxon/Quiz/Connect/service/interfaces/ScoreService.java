package com.galaxon.Quiz.Connect.service.interfaces;

import com.galaxon.Quiz.Connect.model.Score;

import java.util.List;

public interface ScoreService {
    List<Score> getScoresByUserId(Long userId);
}