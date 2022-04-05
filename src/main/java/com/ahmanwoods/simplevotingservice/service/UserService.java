package com.ahmanwoods.simplevotingservice.service;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.exception.ResourceInUseException;
import com.ahmanwoods.simplevotingservice.exception.EntityNotFoundException;
import com.ahmanwoods.simplevotingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(noRollbackFor = ResourceInUseException.class)
    public UserEntity addUser(String username) throws ResourceInUseException {
        String generatedUUID;
        UserEntity user = new UserEntity();
        user.setUsername(username);

        if (userRepository.existsByUsername(username)) {
            throw new ResourceInUseException();
        }

        while (true) {
            generatedUUID = UUID.randomUUID().toString();
            if (!userRepository.existsById(generatedUUID)) {
                user.setId(generatedUUID);
                break;
            }
        }

        userRepository.save(user);
        return user;
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public UserEntity getUser(String userId) throws ResourceInUseException {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty())
        {
            throw new EntityNotFoundException();
        }

        return user.get();
    }

    @Transactional(noRollbackFor = ResourceInUseException.class)
    public UserEntity loginUser(String username) throws EntityNotFoundException {
        UserEntity user = userRepository.findByUsername((username));
        if (user == null)
        {
            throw new EntityNotFoundException();
        }

        return user;
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public UserEntity deleteUser(String username) throws EntityNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new EntityNotFoundException();
        }

        userRepository.delete(user);
        return user;
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public UserEntity updateUser(String username, String newUsername) throws EntityNotFoundException, ResourceInUseException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new EntityNotFoundException();
        }

        if (userRepository.existsByUsername(newUsername))
        {
            throw new ResourceInUseException();
        }

        user.setUsername(newUsername);
        userRepository.save(user);
        return user;
    }
}
