package com.hello.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hello.model.LocalTransfer;

@Repository
public interface LocalTransferRepository extends MongoRepository<LocalTransfer, String> {

	public List<LocalTransfer> findByLogonUsername(String username);

}