package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.VoteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface VoteRepository extends CrudRepository<VoteEntity, String> {

    boolean existsByQuestionIdAndUserId(String questionId, String userId);

    List<VoteEntity> findAllByUserId(String userId);

    int countAllByQuestionIdAndVoteValue(String questionId, int voteValue);
}
