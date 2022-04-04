package com.ahmanwoods.simplevotingservice.repository;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface VoteRepository extends CrudRepository<UserEntity, String> {

}
