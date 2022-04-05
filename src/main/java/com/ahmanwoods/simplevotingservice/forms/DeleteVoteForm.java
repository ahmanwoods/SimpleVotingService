package com.ahmanwoods.simplevotingservice.forms;

import javax.validation.constraints.NotBlank;

public class DeleteVoteForm {

    @NotBlank(message="Vote to be deleted cannot be blank")
    private String vote;

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
