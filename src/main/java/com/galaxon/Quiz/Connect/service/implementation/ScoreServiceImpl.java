package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.model.Score;
import com.galaxon.Quiz.Connect.repository.ScoreRepository;
import com.galaxon.Quiz.Connect.service.interfaces.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    @Override
    public List<Score> getScoresByUserId(Long userId) {
        try {
            return scoreRepository.findByUsers_Id(userId);
        } catch (Exception e) {
            log.error("Error fetching scores for userId {}: {}", userId, e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
