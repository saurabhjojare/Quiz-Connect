package com.galaxon.Quiz.Connect.constants;

public class Routes {
    private Routes() {
    }

    public static final String BASE_API = "/api";

    public static final class Quiz {
        public static final String BASE_QUIZ = "/quiz";
        public static final String QUIZ_ID = "/{quizId}";
        public static final String ADD_QUESTION = QUIZ_ID + "/question";
        public static final String TAKE_QUIZ = QUIZ_ID + "/take";
        public static final String SUBMIT_QUIZ = QUIZ_ID + "/submit";
    }

    public static final class Question {
        public static final String BASE_QUESTION = "/question";
        public static final String QUESTION_ID = "/{questionId}";
    }

    public static final class User {
        public static final String BASE_USER = "/user";
    }

    public static final class Score {
        public static final String BASE_SCORE = "/score";
        public static final String USER_ID = "/{userId}";
    }

}
