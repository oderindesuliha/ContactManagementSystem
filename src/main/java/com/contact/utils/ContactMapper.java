package com.contact.utils;

import com.contact.data.models.Contact;
import com.contact.dtos.requests.ContactRegisterRequest;
import com.contact.dtos.responses.ContactRegisterResponse;

public class ContactMapper {
    public static Contact mapToContact(ContactRegisterRequest contactRequest) {
        Contact contact = new Contact();
        contact.setUserId(contactRequest.getUserId());
        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());
        contact.setEmail(contactRequest.getEmail());
        contact.setPhoneNumber(contactRequest.getPhoneNumber());
        return contact;
    }

    public static ContactRegisterResponse mapToContactResponse(Contact savedContact) {
        ContactRegisterResponse response = new ContactRegisterResponse();
        response.setId(savedContact.getId());
        response.setDateCreated(savedContact.getDateCreated());
        return response;
    }
}

