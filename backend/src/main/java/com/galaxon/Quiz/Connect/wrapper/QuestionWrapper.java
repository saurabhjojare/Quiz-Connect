package com.galaxon.Quiz.Connect.wrapper;

import com.galaxon.Quiz.Connect.constant.Constants;
import com.galaxon.Quiz.Connect.dto.request.QuestionRequest;
import com.galaxon.Quiz.Connect.model.Option;
import com.galaxon.Quiz.Connect.model.Question;
import com.galaxon.Quiz.Connect.model.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionWrapper {

    public static Question toEntity(QuestionRequest request, Quiz quiz) {
        Question question = new Question();
        question.setText(request.getText());
        question.setQuiz(quiz);
        question.setType(request.getType());

        if (question.getType().name().equals(Constants.TEXT)) {
            question.setOptions(Collections.emptyList());
        } else {
            List<Option> options = new ArrayList<>();
            for (QuestionRequest.OptionRequest optReq : request.getOptions()) {
                Option option = new Option();
                option.setText(optReq.getText());
                option.setCorrect(optReq.isCorrect());
                option.setQuestion(question);
                options.add(option);
            }
            question.setOptions(options);
        }

        return question;
    }
}
