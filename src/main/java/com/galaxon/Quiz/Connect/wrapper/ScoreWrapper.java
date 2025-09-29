package com.galaxon.Quiz.Connect.wrapper;

import com.galaxon.Quiz.Connect.dto.response.ScoreResponse;
import com.galaxon.Quiz.Connect.model.Quiz;
import com.galaxon.Quiz.Connect.model.Score;
import com.galaxon.Quiz.Connect.model.Users;

public class ScoreWrapper {

    public static ScoreResponse toResponse(Score score) {
        ScoreResponse scoreResponse = new ScoreResponse();
        scoreResponse.setScoreId(score.getId());
        scoreResponse.setUserId(score.getUsers().getId());
        scoreResponse.setFirstName(score.getUsers().getFirstName());
        scoreResponse.setLastName(score.getUsers().getLastName());
        scoreResponse.setQuizId(score.getQuiz().getId());
        scoreResponse.setQuizTitle(score.getQuiz().getTitle());
        scoreResponse.setScore(score.getScore());
        scoreResponse.setTotal(score.getTotal());
        scoreResponse.setAttemptedAt(score.getAttemptedAt());
        return scoreResponse;
    }

    public static Score toEntity(Quiz quiz, Users user, int scoreValue, int total) {
        Score score = new Score();
        score.setQuiz(quiz);
        score.setUsers(user);
        score.setScore(scoreValue);
        score.setTotal(total);
        return score;
    }
}
