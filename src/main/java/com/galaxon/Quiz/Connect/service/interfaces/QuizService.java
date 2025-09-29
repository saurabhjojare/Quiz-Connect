package com.galaxon.Quiz.Connect.service.interfaces;


import com.galaxon.Quiz.Connect.dto.request.QuizRequest;
import com.galaxon.Quiz.Connect.dto.request.SubmitQuizRequest;
import com.galaxon.Quiz.Connect.dto.response.CreationQuizResponse;
import com.galaxon.Quiz.Connect.dto.response.QuizResponse;
import com.galaxon.Quiz.Connect.dto.response.SubmitQuizResponse;
import com.galaxon.Quiz.Connect.dto.response.TakeQuizResponse;

import java.util.List;

public interface QuizService {
    CreationQuizResponse createQuiz(QuizRequest quizRequest);
    List<CreationQuizResponse> getAllQuizzes();
    TakeQuizResponse takeQuiz(Long quizId);
    SubmitQuizResponse submitQuiz(Long quizId, SubmitQuizRequest submitQuizRequest);
    boolean deleteQuizById(Long quizId);
    CreationQuizResponse updateQuizById(Long quizId);
}
