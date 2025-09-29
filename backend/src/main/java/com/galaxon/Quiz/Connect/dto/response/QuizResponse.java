package com.galaxon.Quiz.Connect.dto.response;

import com.galaxon.Quiz.Connect.enums.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuizResponse {
    private Long id;
    private String title;
    private List<QuestionDTO> questions;

    @Data
    public static class QuestionDTO {
        private Long id;
        private String text;
        private QuestionType type;
        private List<OptionDTO> options;
    }

    @Data
    public static class OptionDTO {
        private Long id;
        private String text;
        private boolean isCorrect;
    }
}
