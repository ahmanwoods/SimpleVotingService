package com.ahmanwoods.simplevotingservice.service;

import com.ahmanwoods.simplevotingservice.entity.VoteEntity;
import com.ahmanwoods.simplevotingservice.exception.ResourceInUseException;
import com.ahmanwoods.simplevotingservice.exception.EntityNotFoundException;
import com.ahmanwoods.simplevotingservice.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.core.io.buffer.DataBufferUtils.matcher;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Transactional(noRollbackFor = ResourceInUseException.class)
    public VoteEntity addVote(String questionId, String userId, int voteValue) throws ResourceInUseException {
        String generatedUUID;
        VoteEntity vote = new VoteEntity("", questionId, userId, voteValue);

        if (voteRepository.existsByQuestionIdAndUserId(questionId, userId))
        {
            throw new ResourceInUseException();
        }

        while (true) {
            generatedUUID = UUID.randomUUID().toString();
            if (!voteRepository.existsById(generatedUUID))
            {
                vote.setId(generatedUUID);
                break;
            }
        }

        voteRepository.save(vote);
        return vote;
    }
    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public List<String> getUserVotes(String userId) throws EntityNotFoundException {
        return Streamable.of(voteRepository.findAllByUserId(userId))
                .map(vote -> vote.getQuestion().getId()).toList();
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public VoteEntity deleteVote(String voteId) throws EntityNotFoundException {
        Optional<VoteEntity> vote = voteRepository.findById(voteId);
        if (!vote.isPresent())
        {
            throw new EntityNotFoundException();
        }

        VoteEntity returnVote = vote.get();
        voteRepository.delete(returnVote);
        return returnVote;
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public VoteEntity updateVote(String voteId, int voteValue) throws EntityNotFoundException, ResourceInUseException {
        Optional<VoteEntity> vote = voteRepository.findById(voteId);
        if (!vote.isPresent())
        {
            throw new EntityNotFoundException();
        }

        VoteEntity returnVote = vote.get();
        returnVote.setVoteValue(voteValue);
        voteRepository.save(returnVote);
        return returnVote;
    }
}
