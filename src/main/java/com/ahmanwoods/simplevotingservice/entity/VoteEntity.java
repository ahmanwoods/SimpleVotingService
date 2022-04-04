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
    private int vote;

    public VoteEntity() {

    }

    public VoteEntity(String id, QuestionEntity question, UserEntity user, int vote) {
        this.id = id;
        this.question = question;
        this.user = user;
        this.vote = vote;
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

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
