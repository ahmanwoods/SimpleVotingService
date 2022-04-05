package com.ahmanwoods.simplevotingservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VoteEntity {

    @Id
    private String id;
    @OneToOne
    private QuestionEntity question;
    @OneToOne
    private UserEntity user;
    private int voteValue;

    public VoteEntity() {}

    public VoteEntity(String id, String questionId, String userId, int voteValue) {
        this.id = id;
        this.question = new QuestionEntity(questionId, "");
        this.user = new UserEntity(userId, "");
        this.voteValue = voteValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int vote) {
        this.voteValue = vote;
    }
}
