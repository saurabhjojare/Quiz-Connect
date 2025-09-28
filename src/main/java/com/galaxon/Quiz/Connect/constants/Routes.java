package com.galaxon.Quiz.Connect.constants;

public class Routes {
    private Routes() {
    }

    public static final String BASE_API = "/api";

    public static final class Quiz {
        public static final String BASE_QUIZ = "/quizzes";
        public static final String ADD_QUESTION = "/{quizId}/questions";
        public static final String TAKE_QUIZ = "/{quizId}/take";
        public static final String SUBMIT_QUIZ = "/{quizId}/submit";
    }

}
