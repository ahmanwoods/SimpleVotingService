package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.QuestionEntity;
import org.springframework.data.repository.CrudRepository;


public interface QuestionRepository extends CrudRepository<QuestionEntity, String> {

    QuestionEntity findByQuestion(String Question);

    boolean existsByQuestion(String question);

}
