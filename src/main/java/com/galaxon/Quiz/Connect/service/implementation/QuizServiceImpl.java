package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.enums.QuestionType;
import com.galaxon.Quiz.Connect.dto.request.QuestionRequest;
import com.galaxon.Quiz.Connect.dto.request.QuizRequest;
import com.galaxon.Quiz.Connect.dto.request.SubmitQuizRequest;
import com.galaxon.Quiz.Connect.dto.response.QuizTakeResponse;
import com.galaxon.Quiz.Connect.dto.response.SubmitQuizResponse;
import com.galaxon.Quiz.Connect.model.Option;
import com.galaxon.Quiz.Connect.model.Question;
import com.galaxon.Quiz.Connect.model.Quiz;
import com.galaxon.Quiz.Connect.repository.QuizRepository;
import com.galaxon.Quiz.Connect.service.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public Quiz createQuiz(QuizRequest quizRequest) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(quizRequest.getTitle());
            return quizRepository.save(quiz);
        } catch (Exception e) {
            log.error("Failed to create quiz with title '{}'", quizRequest.getTitle(), e);
            return null;
        }
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        try {
            return quizRepository.findAll();
        } catch (Exception e) {
            log.error("Failed to fetch quizzes from the database.", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Quiz addQuestionToQuiz(Long quizId, List<QuestionRequest> questionRequests) {
        try {
            // Fetch the quiz
            Quiz quiz = quizRepository.findById(quizId).orElse(null);
            if (quiz == null) {
                log.warn("Quiz not found with id {}", quizId);
                return null;
            }

            if (quiz.getQuestions() == null) {
                quiz.setQuestions(new ArrayList<>());
            }

            for (QuestionRequest questionRequest : questionRequests) {
                Question question = new Question();
                question.setText(questionRequest.getText());
                question.setQuiz(quiz);
                question.setType(questionRequest.getType());

                List<Option> options = new ArrayList<>();

                if (question.getType() == QuestionType.TEXT) {
                    question.setOptions(Collections.emptyList());
                } else {
                    List<QuestionRequest.OptionRequest> optRequests = questionRequest.getOptions();
                    if (optRequests == null || optRequests.isEmpty()) {
                        throw new IllegalArgumentException("Options cannot be empty for choice questions");
                    }

                    if (question.getType() == QuestionType.SINGLE_CHOICE) {
                        long correctCount = 0;
                        for (QuestionRequest.OptionRequest o : optRequests) {
                            if (o.isCorrect()) {
                                correctCount++;
                            }
                            if (correctCount != 1) {
                                throw new IllegalArgumentException("Single choice requires exactly 1 correct option");
                            }
                        }
                    }

                    if (question.getType() == QuestionType.MULTIPLE_CHOICE) {
                        boolean hasCorrect = false;
                        for (QuestionRequest.OptionRequest o : optRequests) {
                            if (o.isCorrect()) {
                                hasCorrect = true;
                            }
                            if (!hasCorrect) {
                                throw new IllegalArgumentException("Multiple choice needs at least 1 correct option");
                            }
                        }
                    }

                    for (QuestionRequest.OptionRequest optReq : optRequests) {
                        Option option = new Option();
                        option.setText(optReq.getText());
                        option.setCorrect(optReq.isCorrect());
                        option.setQuestion(question);
                        options.add(option);
                    }
                    question.setOptions(options);
                }

                quiz.getQuestions().add(question);
            }

            return quizRepository.save(quiz);
        } catch (Exception e) {
            log.error("Unexpected error adding question(s) to quiz {}: {}", quizId, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public QuizTakeResponse takeQuiz(Long quizId) {
        try {
            Quiz quiz = quizRepository.findById(quizId).orElse(null);
            if (quiz == null) {
                log.info("Quiz not found with id {}", quizId);
                return new QuizTakeResponse();
            }

            QuizTakeResponse response = new QuizTakeResponse();
            response.setId(quiz.getId());
            response.setTitle(quiz.getTitle());

            List<QuizTakeResponse.QuestionDTO> questionDTOList = new ArrayList<>();
            for (Question question : quiz.getQuestions()) {
                QuizTakeResponse.QuestionDTO qDTO = new QuizTakeResponse.QuestionDTO();
                qDTO.setId(question.getId());
                qDTO.setText(question.getText());

                List<QuizTakeResponse.OptionDTO> optionDTOList = new ArrayList<>();
                for (Option option : question.getOptions()) {
                    QuizTakeResponse.OptionDTO oDTO = new QuizTakeResponse.OptionDTO();
                    oDTO.setId(option.getId());
                    oDTO.setText(option.getText());
                    optionDTOList.add(oDTO);
                }
                qDTO.setOptions(optionDTOList);
                questionDTOList.add(qDTO);
            }
            response.setQuestions(questionDTOList);
            return response;

        } catch (Exception e) {
            log.error("Error taking quiz with id {}: {}", quizId, e.getMessage(), e);
            return new QuizTakeResponse();
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
            return new SubmitQuizResponse(score, total);
        } catch (Exception e) {
            log.error("Error submitting quiz with id {}: {}", quizId, e.getMessage(), e);
            return new SubmitQuizResponse();
        }
    }

}
