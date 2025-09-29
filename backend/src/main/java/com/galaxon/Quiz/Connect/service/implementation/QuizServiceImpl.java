package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.dto.response.CreationQuizResponse;
import com.galaxon.Quiz.Connect.dto.response.TakeQuizResponse;
import com.galaxon.Quiz.Connect.model.*;
import com.galaxon.Quiz.Connect.repository.ScoreRepository;
import com.galaxon.Quiz.Connect.dto.request.QuizRequest;
import com.galaxon.Quiz.Connect.dto.request.SubmitQuizRequest;
import com.galaxon.Quiz.Connect.dto.response.QuizResponse;
import com.galaxon.Quiz.Connect.dto.response.SubmitQuizResponse;
import com.galaxon.Quiz.Connect.repository.QuizRepository;
import com.galaxon.Quiz.Connect.repository.UserRepository;
import com.galaxon.Quiz.Connect.service.interfaces.QuizService;
import com.galaxon.Quiz.Connect.wrapper.QuizWrapper;
import com.galaxon.Quiz.Connect.wrapper.ScoreWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    @Override
    public CreationQuizResponse createQuiz(QuizRequest quizRequest) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(quizRequest.getTitle());
            Quiz savedQuiz = quizRepository.save(quiz);
            log.info("Quiz created successfully with id {} and title '{}'", savedQuiz.getId(), savedQuiz.getTitle());
            return new CreationQuizResponse(savedQuiz.getId(), savedQuiz.getTitle());
        } catch (Exception e) {
            log.error("Failed to create quiz with title '{}'", quizRequest.getTitle(), e);
            throw e;
        }
    }

    //TODO Paginate results
    @Override
    public List<CreationQuizResponse> getAllQuizzes() {
        try {
            List<Quiz> quizzes = quizRepository.findAll();
            List<CreationQuizResponse> responseList = new ArrayList<>();
            for (Quiz quiz : quizzes) {
                responseList.add(new CreationQuizResponse(quiz.getId(), quiz.getTitle()));
            }

            log.info("Fetched {} quizzes successfully", responseList.size());
            return responseList;
        } catch (Exception e) {
            log.error("Failed to fetch quizzes from the database.", e);
            return new ArrayList<>();
        }
    }

    @Override
    public TakeQuizResponse takeQuiz(Long quizId) {
        try {
            Quiz quiz = quizRepository.findById(quizId).orElse(null);
            if (quiz == null) {
                log.info("Quiz not found with id {}", quizId);
                return new TakeQuizResponse();
            }

            log.info("Quiz fetched successfully with id {}", quizId);
            return QuizWrapper.toTakeResponse(quiz);
        } catch (Exception e) {
            log.error("Error taking quiz with id {}: {}", quizId, e.getMessage(), e);
            return new TakeQuizResponse();
        }
    }

    @Override
    public SubmitQuizResponse submitQuiz(Long quizId, SubmitQuizRequest request) {
        try {
            Quiz quiz = quizRepository.findById(quizId).orElse(null);

            if (quiz == null) {
                log.info("Quiz not found with id {}", quizId);
                return new SubmitQuizResponse();
            }

            Users users = userRepository.findById(request.getUserId()).orElse(null);

            if (users == null) {
                log.info("User not found with id {}", request.getUserId());
                return new SubmitQuizResponse();
            }

            int total = quiz.getQuestions().size();
            int score = 0;

            for (SubmitQuizRequest.AnswerDTO answer : request.getAnswers()) {
                for (Question question : quiz.getQuestions()) {
                    if (question.getId().equals(answer.getQuestionId())) {
                        for (Option option : question.getOptions()) {
                            if (option.getId().equals(answer.getSelectedOptionId()) && option.isCorrect()) {
                                score++;
                            }
                        }
                    }
                }
            }

            Score userScore = ScoreWrapper.toEntity(quiz, users, score, total);
            scoreRepository.save(userScore);
            log.info("User {} submitted quiz {} with score {}/{}", users.getId(), quizId, score, total);
            return new SubmitQuizResponse(score, total);
        } catch (Exception e) {
            log.error("Error submitting quiz with id {}: {}", quizId, e.getMessage(), e);
            return new SubmitQuizResponse();
        }
    }

    @Override
    public boolean deleteQuizById(Long quizId) {
        try {
            if (!quizRepository.existsById(quizId)) {
                log.warn("Quiz not found with id {}", quizId);
                return false;
            }

            quizRepository.deleteById(quizId);
            log.info("Quiz deleted with id {}", quizId);
            return true;
        } catch (Exception e) {
            log.error("Error deleting quiz {}: {}", quizId, e.getMessage(), e);
            return false;
        }
    }

    // TODO: Implement quiz update by ID
    @Override
    public CreationQuizResponse updateQuizById(Long quizId) {
        return null;
    }

}
