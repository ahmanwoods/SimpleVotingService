package com.ahmanwoods.simplevotingservice.service;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.exception.UsernameInUseException;
import com.ahmanwoods.simplevotingservice.exception.EntityNotFoundException;
import com.ahmanwoods.simplevotingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import javax.persistence.LockModeType;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(noRollbackFor = UsernameInUseException.class)
    public UserEntity addUser(String username) throws UsernameInUseException {
        String generatedUUID;
        UserEntity user = new UserEntity();
        user.setUsername(username);

        if (userRepository.existsByUsername(username)) {
            throw new UsernameInUseException();
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
    public UserEntity updateUser(String username, String newUsername) throws EntityNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException();
        }

        user.setUsername(newUsername);
        userRepository.save(user);
        return user;
    }
}
