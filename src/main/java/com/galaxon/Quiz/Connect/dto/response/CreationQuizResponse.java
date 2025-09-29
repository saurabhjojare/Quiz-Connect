package com.galaxon.Quiz.Connect.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreationQuizResponse {
    private Long id;
    private String title;
}
