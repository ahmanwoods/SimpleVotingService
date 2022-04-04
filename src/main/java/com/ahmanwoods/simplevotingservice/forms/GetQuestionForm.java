package com.ahmanwoods.simplevotingservice.forms;

import javax.validation.constraints.NotEmpty;

public class GetQuestionForm {

    @NotEmpty(message="Question cannot be empty")
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
