package com.contact.services;


import com.contact.data.models.Contact;
import com.contact.data.models.User;
import com.contact.dtos.requests.ContactRegisterRequest;

import java.util.List;

public interface ContactService {
Contact saveContact(ContactRegisterRequest contactRequest, User user);
List<Contact> getAllContacts();
Contact getContactById(String id);
void deleteContact(String id);
Contact updateContact(Contact contact);
Contact getContactByFirstNameAndLastName(String firstName, String lastName);
}
