package com.contact.data.repositories;

import com.contact.data.models.Contact;
import com.contact.data.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact,String> {

    Optional<Contact> findByPhoneNumber(String phoneNumber);
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByPerson(Person person);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> findByFirstNameAndLastNameAndPerson(String firstName, String lastName, Person person);
    long countContactByDateCreated(LocalDate dateCreated);
}

