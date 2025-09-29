package com.galaxon.Quiz.Connect.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitQuizResponse {
    private int score;
    private int total;
}
