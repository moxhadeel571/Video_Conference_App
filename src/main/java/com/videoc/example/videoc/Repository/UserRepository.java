package com.videoc.example.videoc.Repository;


import com.videoc.example.videoc.DAO.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'email': ?0}")
    List<User> findByEmails(String email);
    @Query("{'email': ?0}")
    Optional<User> findByEmail(String email);


}
