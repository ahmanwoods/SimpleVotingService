package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.QuestionEntity;
import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface QuestionRepository extends CrudRepository<QuestionEntity, String> {

    public QuestionEntity findByQuestion(String Question);

    public boolean existsByQuestion(String question);

}
