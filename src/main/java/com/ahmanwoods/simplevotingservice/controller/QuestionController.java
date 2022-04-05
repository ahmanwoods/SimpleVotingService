package com.ahmanwoods.simplevotingservice.controller;

import com.ahmanwoods.simplevotingservice.entity.QuestionEntity;
import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.forms.*;
import com.ahmanwoods.simplevotingservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Validated
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(method=RequestMethod.POST, value="/add")
    public ResponseEntity<Void> addQuestion(@Valid @RequestBody AddQuestionForm addQuestionForm) {
        QuestionEntity createdQuestion = questionService.addQuestion(addQuestionForm.getQuestion());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.GET, value="/getUserUnanswered")
    public ResponseEntity<List<QuestionEntity>> getUserUnanswered(@RequestParam String userId) {
        List<QuestionEntity> questions = questionService.getUserUnanswered(userId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, value="/get")
    public ResponseEntity<QuestionEntity> getQuestion(@Valid @RequestBody AddQuestionForm addQuestionForm) {
        QuestionEntity question = questionService.getQuestion(addQuestionForm.getQuestion());
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/delete")
    public ResponseEntity<Void> deleteQuestion(@Valid @RequestBody DeleteQuestionForm deleteQuestionForm) {
        QuestionEntity deletedQuestion = questionService.deleteQuestion(deleteQuestionForm.getQuestion());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/update")
    public ResponseEntity<Void> updateQuestion(@Valid @RequestBody UpdateQuestionForm updateQuestionForm) {
        QuestionEntity updatedQuestion = questionService.updateQuestion(updateQuestionForm.getQuestion(), updateQuestionForm.getNewQuestion());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
