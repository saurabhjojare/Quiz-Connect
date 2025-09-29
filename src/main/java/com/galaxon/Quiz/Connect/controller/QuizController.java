package com.galaxon.Quiz.Connect.controller;

import com.galaxon.Quiz.Connect.dto.response.TakeQuizResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.galaxon.Quiz.Connect.constant.Routes;
import com.galaxon.Quiz.Connect.dto.request.QuizRequest;
import com.galaxon.Quiz.Connect.dto.request.SubmitQuizRequest;
import com.galaxon.Quiz.Connect.dto.response.CreationQuizResponse;
import com.galaxon.Quiz.Connect.dto.response.QuizResponse;
import com.galaxon.Quiz.Connect.dto.response.SubmitQuizResponse;
import com.galaxon.Quiz.Connect.service.implementation.QuizServiceImpl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(Routes.BASE_API + Routes.Quiz.BASE_QUIZ)
public class QuizController {

    private final QuizServiceImpl quizService;

    @PostMapping
    public CreationQuizResponse createQuiz(@Valid @RequestBody QuizRequest quizRequest) {
        return quizService.createQuiz(quizRequest);
    }

    @GetMapping
    public List<CreationQuizResponse> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping(Routes.Quiz.TAKE_QUIZ)
    public TakeQuizResponse takeQuiz(@PathVariable Long quizId) {
        return quizService.takeQuiz(quizId);
    }

    @PostMapping(Routes.Quiz.SUBMIT_QUIZ)
    public SubmitQuizResponse submitQuiz(@PathVariable Long quizId, @Valid @RequestBody SubmitQuizRequest submitQuizRequest) {
        return quizService.submitQuiz(quizId, submitQuizRequest);
    }

    @DeleteMapping(Routes.Quiz.QUIZ_ID)
    public boolean deleteQuiz(@PathVariable Long quizId) {
        return quizService.deleteQuizById(quizId);
    }
}
