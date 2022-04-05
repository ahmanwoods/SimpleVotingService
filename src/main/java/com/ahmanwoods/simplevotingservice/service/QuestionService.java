package com.ahmanwoods.simplevotingservice.service;

import com.ahmanwoods.simplevotingservice.entity.QuestionEntity;
import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.exception.ResourceInUseException;
import com.ahmanwoods.simplevotingservice.exception.EntityNotFoundException;
import com.ahmanwoods.simplevotingservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private VoteService voteService;
    @Autowired
    private UserService userService;
    private final static Pattern UUID_REGEX = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");


    @Transactional(noRollbackFor = ResourceInUseException.class)
    public QuestionEntity addQuestion(String questionString) throws ResourceInUseException {
        String generatedUUID;
        QuestionEntity question = new QuestionEntity();
        question.setQuestion(questionString);

        if (questionRepository.existsByQuestion(questionString))
        {
            throw new ResourceInUseException();
        }

        if (UUID_REGEX.matcher(questionString).matches())
        {
            throw new ResourceInUseException();
        }

        while (true) {
            generatedUUID = UUID.randomUUID().toString();
            if (!questionRepository.existsById(generatedUUID))
            {
                question.setId(generatedUUID);
                break;
            }
        }

        questionRepository.save(question);
        return question;
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public QuestionEntity getQuestion(String questionString) throws EntityNotFoundException {
        Optional<QuestionEntity> question;
        if (UUID_REGEX.matcher(questionString).matches())
        {
            question = questionRepository.findById(questionString);
        }
        else
        {
            question = Optional.ofNullable(questionRepository.findByQuestion(questionString));
        }

        if (question.isEmpty())
        {
            throw new EntityNotFoundException();
        }

        return question.get();
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public List<QuestionEntity> getUserUnanswered(String userId) throws EntityNotFoundException {
        UserEntity user = userService.getUser(userId);
        if (user == null)
        {
            throw new EntityNotFoundException();
        }
        List<QuestionEntity> questions = Streamable.of(questionRepository.findAll()).toList();
        List<String> userVotes = voteService.getUserVotes(userId);

        return questions.stream().filter(q -> !userVotes.contains(q.getId())).toList();
    }


    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public QuestionEntity deleteQuestion(String questionString) throws EntityNotFoundException {
        Optional<QuestionEntity> question;
        if (UUID_REGEX.matcher(questionString).matches())
        {
            question = questionRepository.findById(questionString);
        }
        else
        {
            question = Optional.ofNullable(questionRepository.findByQuestion(questionString));
        }

        if (question.isEmpty())
        {
            throw new EntityNotFoundException();
        }

        QuestionEntity returnQuestion = question.get();
        questionRepository.delete(returnQuestion);
        return returnQuestion;
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public QuestionEntity updateQuestion(String questionString, String newQuestion) throws EntityNotFoundException, ResourceInUseException {
        Optional<QuestionEntity> question;

        if (UUID_REGEX.matcher(questionString).matches())
        {
            throw new ResourceInUseException();
        }

        if (UUID_REGEX.matcher(questionString).matches())
        {
            question = questionRepository.findById(questionString);
        }
        else
        {
            question = Optional.ofNullable(questionRepository.findByQuestion(questionString));
        }

        if (question.isEmpty())
        {
            throw new EntityNotFoundException();
        }

        QuestionEntity returnQuestion = question.get();
        if (questionRepository.existsByQuestion((returnQuestion.getQuestion())))
        {
            throw new ResourceInUseException();
        }

        returnQuestion.setQuestion(newQuestion);
        questionRepository.save(returnQuestion);
        return returnQuestion;
    }
}
