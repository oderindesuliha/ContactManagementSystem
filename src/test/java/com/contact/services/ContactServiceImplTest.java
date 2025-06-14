package com.contact.services;

import com.contact.data.models.Contact;
import com.contact.data.models.User;
import com.contact.data.repositories.Contacts;
import com.contact.dtos.requests.ContactRegisterRequest;
import com.contact.dtos.responses.ContactRegisterResponse;
import com.contact.exceptions.ContactException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactServiceImplTest {
    @Autowired
    private Contacts contacts;
    private ContactRegisterResponse contactResponse;
    private ContactService contactService;
    private ContactRegisterRequest contact;
    private User user;

    @BeforeEach
    public void setUp() {
        contactService = new ContactServiceImpl(contacts);
        contact = new ContactRegisterRequest();
        user = new User();
        user.setUserId("user1");
        contacts.deleteAll();
    }

    @Test
    public void testTosaveContact_returnCount() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");

        Contact response = contactService.saveContact(contact, user);
        assertEquals("Contact successfully saved", contactResponse.getMessage());
        assertEquals(1, contacts.count());
    }

    @Test
    void getAllContacts() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");
        contactService.saveContact(contact, user);

        ContactRegisterRequest contact2 = new ContactRegisterRequest();
        contact2.setFirstName("Joy");
        contact2.setLastName("Duru");
        contact2.setPhoneNumber("09076763421");
        contact2.setEmail("j.duru@gmail.com");
        contact2.setUserId("user1");

        assertEquals(2, contactService.getAllContacts().size());
    }

    @Test
    void getContactById() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");
        Contact saved = contactService.saveContact(contact, user);

        Contact contact = contactService.getContactById(saved.getId());
        assertEquals("Bola", contact.getFirstName());
    }

    @Test
    void deleteContact() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");
        Contact saved = contactService.saveContact(contact, user);

        contactService.deleteContact(saved.getId());
        assertEquals(0, contacts.count());
    }

    @Test
    void updateContact() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");
        Contact saved = contactService.saveContact(contact, user);

        Contact contact = contactService.getContactById(saved.getId());
        contact.setEmail("bola@gmail.com");
        Contact updated = contactService.updateContact(contact);

        assertEquals("bola@gmail.com", updated.getEmail());
    }

    @Test
    void getContactByFirstNameAndLastName() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");
        contactService.saveContact(contact, user);

        Contact contact = contactService.getContactByFirstNameAndLastName("Kemi","Ojo");
        assertEquals("Bola", contact.getFirstName());
    }

    @Test
    void testSaveContact_duplicatePhoneNumber_throwsException() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");
        contactService.saveContact(contact, user);

        ContactRegisterRequest contact2 = new ContactRegisterRequest();
        contact2.setFirstName("Joy");
        contact2.setLastName("Duru");
        contact2.setPhoneNumber("09076763421");
        contact2.setEmail("j.duru@gmail.com");
        contact2.setUserId("user1");

        assertThrows(ContactException.class, () -> contactService.saveContact(contact2, user));
    }

    @Test
    void testSaveContact_nullUser_throwsException() {
        contact.setFirstName("Bola");
        contact.setLastName("Oyetola");
        contact.setPhoneNumber("09076763421");
        contact.setEmail("b.oyetola@gmail.com");
        contact.setUserId("user1");

        assertThrows(ContactException.class, () -> contactService.saveContact(contact, null));
    }
}