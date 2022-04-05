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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @RequestMapping(method=RequestMethod.GET, value="/countQuestionVotes")
    public ResponseEntity<String> countVotes(@RequestParam String questionId) {
        int yesVotes = voteService.countQuestionVotesByVoteValue(questionId, 1);
        int noVotes = voteService.countQuestionVotesByVoteValue(questionId, 0);

        float totalVotes = yesVotes + noVotes;

        String yesPercentage = String.format("%.0f%%", (yesVotes / totalVotes)*100);
        String noPercentage = String.format("%.0f%%", (noVotes / totalVotes)*100);

        Map<String, Object> resData = new HashMap<String, Object>();
        resData.put("yes_votes", yesVotes);
        resData.put("yes_percentage", yesPercentage);
        resData.put("no_votes", noVotes);
        resData.put("no_percentage", noPercentage);

        // Manually building JSON to avoid extraneous import for onetime functionality.
        String resJson = "{" + resData.entrySet().stream()
                .map(mapEntry -> String.format("\"%s\": \"%s\"", mapEntry.getKey(), mapEntry.getValue()))
                .collect(Collectors.joining(", ")) + "}";

        return new ResponseEntity<>(resJson, HttpStatus.OK);
    }

}
