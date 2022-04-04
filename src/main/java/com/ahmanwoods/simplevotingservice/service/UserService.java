package com.ahmanwoods.simplevotingservice.service;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.exception.UsernameInUseException;
import com.ahmanwoods.simplevotingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
