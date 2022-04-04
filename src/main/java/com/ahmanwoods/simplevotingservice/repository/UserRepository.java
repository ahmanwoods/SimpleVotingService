package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    public UserEntity findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsById(String id);

}
