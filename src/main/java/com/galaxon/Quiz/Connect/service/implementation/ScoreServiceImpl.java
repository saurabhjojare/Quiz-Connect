package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.dto.response.ScoreResponse;
import com.galaxon.Quiz.Connect.model.Score;
import com.galaxon.Quiz.Connect.repository.ScoreRepository;
import com.galaxon.Quiz.Connect.service.interfaces.ScoreService;
import com.galaxon.Quiz.Connect.wrapper.ScoreWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    @Override
    public List<ScoreResponse> getScoresByUserId(Long userId) {
        try {
            List<Score> scores = scoreRepository.findByUsers_Id(userId);
            log.info("Fetched scores for userId: {}", userId);

            List<ScoreResponse> responseList = new ArrayList<>();
            for (Score score : scores) {
                responseList.add(ScoreWrapper.toResponse(score));
            }
            return responseList;
        } catch (Exception e) {
            log.error("Error fetching scores for userId {}: {}", userId, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    //TODO Paginate results
    @Override
    public List<ScoreResponse> getAllScores() {
        try {
            List<Score> scores = scoreRepository.findAll();
            log.info("Fetched scores for all users");

            List<ScoreResponse> responseList = new ArrayList<>();
            for (Score score : scores) {
                responseList.add(ScoreWrapper.toResponse(score));
            }
            return responseList;
        } catch (Exception e) {
            log.error("Error fetching scores {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // TODO: Implement score deletion by ID
    @Override
    public boolean deleteScoreById(Long scoreId) {
        return false;
    }

    // TODO: Implement score update for a user
    @Override
    public ScoreResponse updateScoreByUserId(Long userId) {
        return null;
    }
}
