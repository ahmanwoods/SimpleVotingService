package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.entity.VoteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface VoteRepository extends CrudRepository<VoteEntity, String> {

    public boolean existsByQuestionIdAndUserId(String questionId, String userId);

    public List<VoteEntity> findAllByUserId(String userId);
}
