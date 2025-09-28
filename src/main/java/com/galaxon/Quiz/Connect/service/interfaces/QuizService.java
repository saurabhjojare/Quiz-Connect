package com.galaxon.Quiz.Connect.service.interfaces;


import com.galaxon.Quiz.Connect.dto.request.QuestionRequest;
import com.galaxon.Quiz.Connect.dto.request.QuizRequest;
import com.galaxon.Quiz.Connect.dto.request.SubmitQuizRequest;
import com.galaxon.Quiz.Connect.dto.response.QuizTakeResponse;
import com.galaxon.Quiz.Connect.dto.response.SubmitQuizResponse;
import com.galaxon.Quiz.Connect.model.Quiz;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(QuizRequest quizRequest);

    List<Quiz> getAllQuizzes();

    Quiz addQuestionToQuiz(Long quizId, List<QuestionRequest> questionRequest);

    QuizTakeResponse takeQuiz(Long quizId);

    SubmitQuizResponse submitQuiz(Long quizId, SubmitQuizRequest request);
}
