package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.dto.request.SubmitQuizRequest;
import com.galaxon.Quiz.Connect.dto.response.SubmitQuizResponse;
import com.galaxon.Quiz.Connect.model.Option;
import com.galaxon.Quiz.Connect.model.Question;
import com.galaxon.Quiz.Connect.model.Quiz;
import com.galaxon.Quiz.Connect.model.Users;
import com.galaxon.Quiz.Connect.repository.QuizRepository;
import com.galaxon.Quiz.Connect.repository.ScoreRepository;
import com.galaxon.Quiz.Connect.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private QuizServiceImpl quizService;

    private Quiz quiz;
    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup user
        user = new Users();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        quiz = new Quiz();
        quiz.setId(101L);

        Question question = new Question();
        question.setId(201L);

        Option option1 = new Option();
        option1.setId(301L);
        option1.setText("Correct Option");
        option1.setCorrect(true);

        Option option2 = new Option();
        option2.setId(302L);
        option2.setText("Wrong Option");
        option2.setCorrect(false);

        question.setOptions(new ArrayList<>());
        question.getOptions().add(option1);
        question.getOptions().add(option2);

        quiz.setQuestions(new ArrayList<>());
        quiz.getQuestions().add(question);
    }

    @Test
    void testSubmitQuiz_AllCorrectAnswers() {
        SubmitQuizRequest.AnswerDTO answer = new SubmitQuizRequest.AnswerDTO();
        answer.setQuestionId(201L);
        answer.setSelectedOptionId(301L); // Correct option

        SubmitQuizRequest request = new SubmitQuizRequest();
        request.setUserId(1L);
        request.setAnswers(Collections.singletonList(answer));

        when(quizRepository.findById(101L)).thenReturn(Optional.of(quiz));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        SubmitQuizResponse response = quizService.submitQuiz(101L, request);

        assertEquals(1, response.getScore());
        assertEquals(1, response.getTotal());
    }

    @Test
    void testSubmitQuiz_WrongAnswer() {
        SubmitQuizRequest.AnswerDTO answer = new SubmitQuizRequest.AnswerDTO();
        answer.setQuestionId(201L);
        answer.setSelectedOptionId(302L); // Wrong option

        SubmitQuizRequest request = new SubmitQuizRequest();
        request.setUserId(1L);
        request.setAnswers(Collections.singletonList(answer));

        when(quizRepository.findById(101L)).thenReturn(Optional.of(quiz));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        SubmitQuizResponse response = quizService.submitQuiz(101L, request);

        assertEquals(0, response.getScore());
        assertEquals(1, response.getTotal());
    }

    @Test
    void testSubmitQuiz_PartiallyCorrectAnswers() {
        Question question2 = new Question();
        question2.setId(202L);
        Option q2Option1 = new Option();
        q2Option1.setId(303L);
        q2Option1.setText("Correct Option");
        q2Option1.setCorrect(true);

        Option q2Option2 = new Option();
        q2Option2.setId(304L);
        q2Option2.setText("Wrong Option");
        q2Option2.setCorrect(false);

        question2.setOptions(new ArrayList<>());
        question2.getOptions().add(q2Option1);
        question2.getOptions().add(q2Option2);

        quiz.getQuestions().add(question2); // Now 2 questions

        // User answers: first correct, second wrong
        SubmitQuizRequest.AnswerDTO answer1 = new SubmitQuizRequest.AnswerDTO();
        answer1.setQuestionId(201L);
        answer1.setSelectedOptionId(301L); // correct

        SubmitQuizRequest.AnswerDTO answer2 = new SubmitQuizRequest.AnswerDTO();
        answer2.setQuestionId(202L);
        answer2.setSelectedOptionId(304L); // wrong

        SubmitQuizRequest request = new SubmitQuizRequest();
        request.setUserId(1L);
        request.setAnswers(Arrays.asList(answer1, answer2));

        when(quizRepository.findById(101L)).thenReturn(Optional.of(quiz));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        SubmitQuizResponse response = quizService.submitQuiz(101L, request);

        assertEquals(1, response.getScore());
        assertEquals(2, response.getTotal());
    }

}
