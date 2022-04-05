package com.ahmanwoods.simplevotingservice.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddVoteForm {

    @NotBlank(message="Question to be voted on cannot be blank")
    private String question;
    @NotBlank(message="User voting on question cannot be blank")
    private String user;
    @NotNull(message="Vote cannot be blank")
    private int vote;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
