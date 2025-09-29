package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.dto.request.QuestionRequest;
import com.galaxon.Quiz.Connect.dto.response.QuizResponse;
import com.galaxon.Quiz.Connect.model.Question;
import com.galaxon.Quiz.Connect.model.Quiz;
import com.galaxon.Quiz.Connect.repository.QuestionRepository;
import com.galaxon.Quiz.Connect.repository.QuizRepository;
import com.galaxon.Quiz.Connect.service.interfaces.QuestionService;
import com.galaxon.Quiz.Connect.wrapper.QuestionWrapper;
import com.galaxon.Quiz.Connect.wrapper.QuizWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Transactional
    @Override
    public QuizResponse addQuestionToQuiz(Long quizId, List<QuestionRequest> questionRequests) {
        try {
            Quiz quiz = quizRepository.findById(quizId).orElse(null);
            if (quiz == null) {
                log.warn("Quiz not found with id {}", quizId);
                return new QuizResponse();
            }

            if (quiz.getQuestions() == null) {
                quiz.setQuestions(new ArrayList<>());
            }

            for (QuestionRequest req : questionRequests) {
                Question question = QuestionWrapper.toEntity(req, quiz);
                quiz.getQuestions().add(question);
            }

            Quiz savedQuiz = quizRepository.save(quiz);
            log.info("Added {} question(s) to quiz {}", questionRequests.size(), quizId);
            return QuizWrapper.toResponse(savedQuiz);
        } catch (Exception e) {
            log.error("Unexpected error adding question(s) to quiz {}: {}", quizId, e.getMessage(), e);
            return new QuizResponse();
        }
    }

    @Override
    public boolean deleteQuestionById(Long questionId) {
        try {
            if (!questionRepository.existsById(questionId)) {
                log.warn("Question not found with id {}", questionId);
                return false;
            }
            questionRepository.deleteById(questionId);
            log.info("Question deleted with id {}", questionId);
            return true;
        } catch (Exception e) {
            log.error("Error deleting question {}: {}", questionId, e.getMessage(), e);
            return false;
        }
    }

    // TODO: Implement question update by ID
    @Override
    public Question updateQuestionById(Long questionId) {
        return null;
    }

    // TODO: Implement get question by quiz ID
    @Override
    public QuizResponse getQuestionsByQuizId(Long quizId) {
        return null;
    }

}
