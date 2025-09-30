package com.galaxon.Quiz.Connect.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class SubmitQuizRequest {

    @NotEmpty(message = "At least one answer must be provided")
    private List<AnswerDTO> answers;

    @NotNull(message = "User ID is required")
    private Long userId;

    @Data
    public static class AnswerDTO {
        @NotNull(message = "Question ID is required")
        private Long questionId;

        @NotNull(message = "Selected option ID is required")
        private Long selectedOptionId;
    }
}
