package com.galaxon.Quiz.Connect.service.interfaces;

import com.galaxon.Quiz.Connect.dto.request.QuestionRequest;
import com.galaxon.Quiz.Connect.dto.response.QuizResponse;
import com.galaxon.Quiz.Connect.model.Question;
import java.util.List;

public interface QuestionService {
    boolean deleteQuestionById(Long questionId);
    Question updateQuestionById(Long questionId);
    QuizResponse addQuestionToQuiz(Long quizId, List<QuestionRequest> questionRequest);
    Question getQuestionsByQuizId(Long quizId);
}
