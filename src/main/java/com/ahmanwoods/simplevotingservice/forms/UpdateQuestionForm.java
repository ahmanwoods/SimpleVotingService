package com.ahmanwoods.simplevotingservice.forms;

import javax.validation.constraints.NotEmpty;

public class UpdateQuestionForm {

    @NotEmpty(message="Question cannot be empty")
    private String question;

    @NotEmpty(message="New question cannot be empty")
    private String newQuestion;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(String newQuestion) {
        this.newQuestion = newQuestion;
    }
}
