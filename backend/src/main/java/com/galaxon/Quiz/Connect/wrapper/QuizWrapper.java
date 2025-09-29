package com.galaxon.Quiz.Connect.wrapper;

import com.galaxon.Quiz.Connect.dto.response.QuizResponse;
import com.galaxon.Quiz.Connect.dto.response.TakeQuizResponse;
import com.galaxon.Quiz.Connect.model.Option;
import com.galaxon.Quiz.Connect.model.Question;
import com.galaxon.Quiz.Connect.model.Quiz;
import java.util.ArrayList;
import java.util.List;

public class QuizWrapper {

    public static QuizResponse toResponse(Quiz quiz) {
        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setId(quiz.getId());
        quizResponse.setTitle(quiz.getTitle());

        List<QuizResponse.QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            QuizResponse.QuestionDTO questionDTO = new QuizResponse.QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setText(question.getText());
            questionDTO.setType(question.getType());

            List<QuizResponse.OptionDTO> optionDTOList = new ArrayList<>();
            for (Option option : question.getOptions()) {
                QuizResponse.OptionDTO optionDTO = new QuizResponse.OptionDTO();
                optionDTO.setId(option.getId());
                optionDTO.setText(option.getText());
                optionDTO.setCorrect(option.isCorrect());
                optionDTOList.add(optionDTO);
            }
            questionDTO.setOptions(optionDTOList);
            questionDTOList.add(questionDTO);
        }
        quizResponse.setQuestions(questionDTOList);
        return quizResponse;
    }

    public static TakeQuizResponse toTakeResponse(Quiz quiz) {
        TakeQuizResponse response = new TakeQuizResponse();
        response.setId(quiz.getId());
        response.setTitle(quiz.getTitle());

        List<TakeQuizResponse.QuestionDTO> questions = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            TakeQuizResponse.QuestionDTO qDto = new TakeQuizResponse.QuestionDTO();
            qDto.setId(question.getId());
            qDto.setText(question.getText());
            qDto.setType(question.getType().name());

            List<TakeQuizResponse.OptionDTO> options = new ArrayList<>();
            for (Option option : question.getOptions()) {
                TakeQuizResponse.OptionDTO oDto = new TakeQuizResponse.OptionDTO();
                oDto.setId(option.getId());
                oDto.setText(option.getText());
                options.add(oDto);
            }
            qDto.setOptions(options);
            questions.add(qDto);
        }
        response.setQuestions(questions);
        return response;
    }
}
