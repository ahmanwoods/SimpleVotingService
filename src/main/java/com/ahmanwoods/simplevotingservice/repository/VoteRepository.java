package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.VoteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRepository extends CrudRepository<VoteEntity, String> {
    int countAllByQuestionIdAndVoteValue(String questionId, int voteValue);
    boolean existsByQuestionIdAndUserId(String questionId, String userId);
    List<VoteEntity> findAllByUserId(String userId);
}
