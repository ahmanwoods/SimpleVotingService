package com.ahmanwoods.simplevotingservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionEntity {

    @Id
    private String id;
    private String question;

    public QuestionEntity() {}

    public QuestionEntity(String id, String question) {
        this.id = id;
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
