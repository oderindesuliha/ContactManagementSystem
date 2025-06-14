package com.contact.data.repositories;

import com.contact.data.models.Contact;
import com.contact.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactsTest {
    @Autowired
    private Contacts contactRepository;
    @Autowired
    private Users users;

    @BeforeEach
    public void setUp() {
        contactRepository.deleteAll();
        users.deleteAll();
    }

    @Test
    public void saveTest() {
        Contact contact = new Contact();
        contact.setFirstName("Bola");
        contact.setLastName("Tinubu");
        contact.setEmail("b@gmail.com");
        contact.setPhoneNumber("09090909090");
        contact.setDateCreated(LocalDate.of(2025, 6, 5));
        Contact savedContact = contactRepository.save(contact);
        assertEquals(1, contactRepository.count());
        assertNotNull(savedContact.getId());
    }

    @Test
    public void testToSaveContact_findById() {
        Contact contact = new Contact();
        contact.setFirstName("Bola");
        contact.setLastName("Tinubu");
        Contact savedContact = contactRepository.save(contact);
        Optional<Contact> foundContact = contactRepository.findById(String.valueOf(savedContact.getId()));
        assertTrue(foundContact.isPresent());
    }

    @Test
    public void testToSaveContact_findByFirstNameAndLastName_returnCount() {
        Contact contact1 = new Contact();
        contact1.setFirstName("Bola");
        contact1.setLastName("Tinubu");
        contact1.setEmail("bt@gmail.com");
        contact1.setPhoneNumber("09090909090");
        contact1.setDateCreated(LocalDate.of(2025, 6, 5));
        contactRepository.save(contact1);
        assertEquals(1, contactRepository.count());

        Contact contact2 = new Contact();
        contact2.setFirstName("Yewande");
        contact2.setLastName("Tinubu");
        contact2.setPhoneNumber("08081828183");
        contact2.setDateCreated(LocalDate.of(2025, 6, 5));
        contact2.setEmail("Yewande@yahoo.com");
        contactRepository.save(contact2);
        assertEquals(2, contactRepository.count());

        Contact contact3 = new Contact();
        contact3.setFirstName("Tunde");
        contact3.setLastName("Taiwo");
        contact3.setPhoneNumber("09090563473");
        contact3.setDateCreated(LocalDate.of(2025, 6, 5));
        contactRepository.save(contact3);
        assertEquals(3, contactRepository.count());

        Optional<Contact> foundContact = contactRepository.findByFirstNameAndLastName("Bola", "Tinubu");
        assertEquals(1, foundContact.get().getId());
        assertTrue(true);
    }

    @Test
    public void testToSaveContact_findById_returnCount() {
        Contact contact1 = new Contact();
        contact1.setFirstName("Bola");
        contact1.setLastName("Tinubu");
        contact1.setEmail("bt@gmail.com");
        contact1.setPhoneNumber("09090909090");
        contact1.setDateCreated(LocalDate.of(2025, 6, 5));
        contactRepository.save(contact1);
        assertEquals(1, contactRepository.count());

        Contact contact2 = new Contact();
        contact2.setFirstName("Yewande");
        contact2.setLastName("Tinubu");
        contact2.setPhoneNumber("08081828183");
        contact2.setDateCreated(LocalDate.of(2025, 6, 5));
        contact2.setEmail("Yewande@yahoo.com");
        contactRepository.save(contact2);
        assertEquals(2, contactRepository.count());

    }

    @Test
    public void testToSaveContact_findByFirstNameAndLastName_returnEmpty() {
        Contact contact2 = new Contact();
        contact2.setFirstName("Yewande");
        contact2.setLastName("Tinubu");
        contact2.setPhoneNumber("08081828183");
        contact2.setDateCreated(LocalDate.of(2025, 6, 5));
        contact2.setEmail("Yewande@yahoo.com");
        contactRepository.save(contact2);

        Optional<Contact> findContact = contactRepository.findByFirstNameAndLastName("Bola", "Tinubu");
        assertTrue(findContact.isEmpty());
    }

    @Test
    public void testToSaveContact_findByPhoneNumber_returnNumber() {
        Contact contact1 = new Contact();
        contact1.setFirstName("Yewande");
        contact1.setLastName("Tinubu");
        contact1.setPhoneNumber("08081828183");
        contact1.setDateCreated(LocalDate.of(2025, 6, 5));
        contact1.setEmail("Yewande@yahoo.com");
        contactRepository.save(contact1);
        assertEquals(1, contactRepository.count());

        Contact contact2 = new Contact();
        contact2.setFirstName("Tunde");
        contact2.setLastName("Taiwo");
        contact2.setPhoneNumber("09090563473");
        contact2.setDateCreated(LocalDate.of(2025, 6, 5));
        contactRepository.save(contact2);
        assertEquals(2, contactRepository.count());

        Optional<Contact> foundContact = contactRepository.findByPhoneNumber("09090563473");
        assertTrue(foundContact.isPresent());
        assertEquals("09090563473", foundContact.get().getPhoneNumber());
    }

      @Test
    public void testToFindContact_findByUser_returnCount() {
        User user1 = new User();
        user1.setFirstName("Bola");
        users.save(user1);
        assertEquals(1, users.count());

        User user2 = new User();
        user2.setFirstName("Kay");
        users.save(user2);
        assertEquals(2, users.count());

        Contact contact = new Contact();
        contact.setFirstName("Yewande");
        contact.setLastName("Tinubu");
        contact.setPhoneNumber("08081828183");
        contact.setDateCreated(LocalDate.of(2025, 6, 5));
        contact.setUser(user2);
        contactRepository.save(contact);

        Contact contact1 = new Contact();
        contact1.setFirstName("Ayo");
        contact1.setPhoneNumber("07081288183");
        contact1.setDateCreated(LocalDate.of(2025, 6, 6));
        contact1.setUser(user1);
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Tunde");
        contact2.setPhoneNumber("07081828183");
        contact2.setDateCreated(LocalDate.of(2025, 6, 5));
        contact2.setUser(user2);
        contactRepository.save(contact2);

        List<Contact> getContacts = contactRepository.findByUser(user1);
        assertEquals(1, getContacts.size());

        List<Contact> getPerson2Contacts = contactRepository.findByUser(user2);
        assertEquals(2, getPerson2Contacts.size());
    }

    @Test
    public void testToSaveContacts_findByDateCreated_returnCount() {
        Contact contact = new Contact();
        contact.setFirstName("Yewande");
        contact.setLastName("Tinubu");
        contact.setPhoneNumber("08081828183");
        contact.setDateCreated(LocalDate.of(2025, 6, 5));
        contactRepository.save(contact);

        Contact contact1 = new Contact();
        contact1.setFirstName("Ayo");
        contact1.setPhoneNumber("07081288183");
        contact1.setDateCreated(LocalDate.of(2025, 6, 6));
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Tunde");
        contact2.setPhoneNumber("07081828183");
        contact2.setDateCreated(LocalDate.of(2025, 6, 5));
        contactRepository.save(contact2);

        var numbersSavedOnThe6thOfJune = contactRepository.countContactByDateCreated(LocalDate.of(2025, 6, 6));
        assertEquals(1, numbersSavedOnThe6thOfJune);
    }
}