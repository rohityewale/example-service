package com.example.cloud.exampleservice.repository;


import com.example.cloud.exampleservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String userName);
}
