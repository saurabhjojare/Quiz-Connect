package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.repository.QuestionRepository;
import com.galaxon.Quiz.Connect.service.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public boolean deleteQuestionById(Long questionId) {
        try {
            if (!questionRepository.existsById(questionId)) {
                log.warn("Question not found with id {}", questionId);
                return false;
            }
            questionRepository.deleteById(questionId);
            return true;
        } catch (Exception e) {
            log.error("Error deleting question {}: {}", questionId, e.getMessage(), e);
            return false;
        }
    }
}
