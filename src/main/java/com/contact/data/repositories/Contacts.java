package com.contact.data.repositories;

import com.contact.data.models.Contact;
import com.contact.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Contacts extends MongoRepository<Contact,String> {

    Optional<Contact> findByPhoneNumber(String phoneNumber);

    List<Contact> findByUser(User user);

    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

    long countContactByDateCreated(LocalDate dateCreated);

    boolean existsByUserId(String userId);
}
