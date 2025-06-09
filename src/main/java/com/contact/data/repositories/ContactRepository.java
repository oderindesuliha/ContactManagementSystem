package com.contact.data.repositories;

import com.contact.data.models.Contact;
import com.contact.data.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    Optional<Contact> findByPhoneNumber(String phoneNumber);
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByLastName(String lastName);
    List<Contact> findByPerson(Person person);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> findByFirstNameAndLastNameAndPerson(String firstName, String lastName, Person person);
    long countContactByDateCreated(LocalDate dateCreated);
}

