package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, String> {

    UserEntity findByUsername(String username);

    boolean existsByUsername(String username);

}
