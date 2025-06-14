package com.contact.services;

import com.contact.data.models.Contact;
import com.contact.data.models.User;
import com.contact.data.repositories.Contacts;
import com.contact.dtos.requests.ContactRegisterRequest;
import com.contact.dtos.responses.ContactRegisterResponse;
import com.contact.exceptions.ContactException;
import com.contact.utils.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validations.ContactValidation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    private final Contacts contacts;

    @Autowired
    public ContactServiceImpl(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactRegisterResponse saveContact(ContactRegisterRequest contactRequest, User user) {
        ContactValidation.validateContact(contactRequest);

        if (user == null) {
            throw new ContactException("User not found");
        }

        if (contacts.findByPhoneNumber(contactRequest.getPhoneNumber()).isPresent()) {
            throw new ContactException("Contact with this phone number already exists");
        }

        Contact contact = ContactMapper.mapToContact(contactRequest);
//        contact.setUser(user);
        contact.setUserId(user.getUserId());
        contact.setDateCreated(LocalDate.now());
        Contact savedContact = contacts.save(contact);

        ContactRegisterResponse response = ContactMapper.mapToContactResponse(savedContact);
        response.setId(savedContact.getId());
        response.setMessage("Contact successfully saved");

        return response;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts.findAll();
    }

    @Override
    public Contact getContactById(String id) {
        Optional<Contact> contact = contacts.findById(id);
        if (contact.isEmpty()) {
            throw new ContactException("Contact with ID: " + id + " not found");
        }
        return contact.get();
    }

    @Override
    public void deleteContact(String id) {
        if (!contacts.existsById(id)) {
            throw new ContactException("Contact with ID " + id + " not found");
        }
        contacts.deleteById(id);
    }

    @Override
    public Contact updateContact(Contact contact) {
        if (!contacts.existsById(contact.getId())) {
            throw new ContactException("Contact with ID: " + contact.getId() + " not found");
        }
        if (contacts.findByPhoneNumber(contact.getPhoneNumber()).isPresent() &&
                !contacts.findByPhoneNumber(contact.getPhoneNumber()).get().getId().equals(contact.getId())) {
            throw new ContactException("Another contact with this phone number exists");
        }
        return contacts.save(contact);
    }

    @Override
    public Contact getContactByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Contact> contact = contacts.findByFirstNameAndLastName(firstName, lastName);
        if (contact.isEmpty()) {
            throw new ContactException("Contact not found");
        }
        return contact.get();
    }
}