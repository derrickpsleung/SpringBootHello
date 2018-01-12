package com.hello.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hello.model.BaseUser;

@Repository
public interface BaseUserRepository extends MongoRepository<BaseUser, String> {

	public BaseUser findByUsername(String username);
    public List<BaseUser> findByUsernameRegex(String username);

}