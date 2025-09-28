package com.galaxon.Quiz.Connect.controller;

import com.galaxon.Quiz.Connect.constants.Routes;
import com.galaxon.Quiz.Connect.model.Score;
import com.galaxon.Quiz.Connect.service.implementation.ScoreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.BASE_API + Routes.Score.BASE_SCORE)
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreServiceImpl scoreService;

    @GetMapping(Routes.Score.USER_ID)
    public List<Score> getScoresByUserId(@PathVariable Long userId) {
        return scoreService.getScoresByUserId(userId);
    }
}
