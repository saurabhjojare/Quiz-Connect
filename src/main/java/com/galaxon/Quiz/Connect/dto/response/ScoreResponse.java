package com.galaxon.Quiz.Connect.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponse {
    private Long scoreId;
    private Long quizId;
    private String quizTitle;
    private Long userId;
    private String firstName;
    private String lastName;
    private int score;
    private int total;
    private LocalDateTime attemptedAt;
}
