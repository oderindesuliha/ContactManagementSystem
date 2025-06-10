package com.contact.data.repositories;

import com.contact.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Users extends MongoRepository<User, String> {
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);


}
