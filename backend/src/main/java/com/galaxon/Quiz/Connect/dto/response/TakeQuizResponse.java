package com.galaxon.Quiz.Connect.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TakeQuizResponse {
    private Long id;
    private String title;
    private List<QuestionDTO> questions;

    @Data
    public static class QuestionDTO {
        private Long id;
        private String text;
        private String type;
        private List<OptionDTO> options;
    }

    @Data
    public static class OptionDTO {
        private Long id;
        private String text;
    }
}
