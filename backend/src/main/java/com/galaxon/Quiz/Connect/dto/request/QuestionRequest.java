package com.galaxon.Quiz.Connect.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.galaxon.Quiz.Connect.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {

    @NotBlank
    @Size(max = 300, message = "Question text cannot exceed 300 characters")
    private String text;

    @NotNull(message = "Question type is required")
    private QuestionType type;

    private List<OptionRequest> options;

    @Data
    public static class OptionRequest {
        @NotBlank(message = "Option text cannot be blank")
        @Size(max = 100, message = "Option text cannot exceed 100 characters")
        private String text;

        @JsonProperty("isCorrect")
        private boolean isCorrect;
    }
}

