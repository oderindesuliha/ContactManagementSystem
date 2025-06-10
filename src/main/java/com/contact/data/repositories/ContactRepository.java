package com.contact.data.repositories;

import com.contact.data.models.Contact;
import com.contact.data.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact,String> {

    Optional<Contact> findByPhoneNumber(String phoneNumber);
    List<Contact> findByPerson(Person person);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    long countContactByDateCreated(LocalDate dateCreated);
    boolean existsById(String id);
    Optional<Contact> findById(String id);
}

