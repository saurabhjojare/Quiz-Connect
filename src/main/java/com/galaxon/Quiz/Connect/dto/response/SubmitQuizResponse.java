package com.galaxon.Quiz.Connect.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitQuizResponse {
    private int score;
    private int total;
}
