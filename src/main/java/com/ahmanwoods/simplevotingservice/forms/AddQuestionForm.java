package com.ahmanwoods.simplevotingservice.forms;

import javax.validation.constraints.NotBlank;

public class AddQuestionForm {
    @NotBlank(message="Question cannot be empty")
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
