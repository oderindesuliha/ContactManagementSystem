package com.contact.data.repositories;

import com.contact.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface Users extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    User findByPhoneNumber(String phoneNumber);
    User findByEmail(String email);
}
