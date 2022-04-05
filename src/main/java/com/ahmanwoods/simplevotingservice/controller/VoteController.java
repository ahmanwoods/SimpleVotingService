package com.ahmanwoods.simplevotingservice.controller;

import com.ahmanwoods.simplevotingservice.entity.VoteEntity;
import com.ahmanwoods.simplevotingservice.forms.*;
import com.ahmanwoods.simplevotingservice.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/vote")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Validated
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(method=RequestMethod.POST, value="/add")
    public ResponseEntity<Void> addVote(@Valid @RequestBody AddVoteForm addVoteForm) {
        VoteEntity createdVote = voteService.addVote(addVoteForm.getQuestion(), addVoteForm.getUser(), addVoteForm.getVote());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/delete")
    public ResponseEntity<Void> deleteVote(@Valid @RequestBody DeleteVoteForm deleteVoteForm) {
        VoteEntity deletedVote = voteService.deleteVote(deleteVoteForm.getVote());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/update")
    public ResponseEntity<Void> updateVote(@Valid @RequestBody UpdateVoteForm updateVoteForm) {
        VoteEntity updatedVote = voteService.updateVote(updateVoteForm.getVote(), updateVoteForm.getVoteValue());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
