package com.ahmanwoods.simplevotingservice.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateVoteForm {

    @NotBlank(message="Vote to be update cannot be blank")
    private String vote;
    @NotNull(message="Updated vote cannot be blank")
    private int voteValue;

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public int getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }
}
