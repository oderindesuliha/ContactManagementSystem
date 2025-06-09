package com.contact.data.repositories;

import com.contact.data.models.Contact;
import com.contact.data.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        contactRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        Contact contact = new Contact();
        contact.setFirstName("Bola");
        contact.setLastName("Tinubu");
        contact.setEmail("b@gmail.com");
        contact.setPhoneNumber("09090909090");
        contact.setDateCreated(LocalDate.of(2025, 6, 5));
        var savedContact = contactRepository.save(contact);
        assertEquals(1, contactRepository.count());
        assertNotNull(savedContact);
    }

    @Test
    public void testToSaveContact_findById() {
        Contact contact = new Contact();
        contact.setFirstName("Bola");
        contact.setLastName("Tinubu");
        Contact savedContact = contactRepository.save(contact);
        Optional<Contact> foundContact = contactRepository.findById(savedContact.getId());
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

        List<Contact> foundContact = contactRepository.findByFirstNameAndLastName("Bola", "Tinubu");
        assertEquals(1, foundContact.size());
        assertTrue(foundContact.contains(contact1));
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

        assertTrue(contactRepository.existsById(contact1.getId()));
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

        List<Contact> findContact = contactRepository.findByFirstNameAndLastName("Bola", "Tinubu");
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
    public void testToFindContact_findByPerson_returnCount() {
        Person person1 = new Person();
        person1.setFirstName("Bola");
        personRepository.save(person1);
        assertEquals(1, personRepository.count());

        Person person2 = new Person();
        person2.setFirstName("Kay");
        personRepository.save(person2);
        assertEquals(2, personRepository.count());

        Contact contact = new Contact();
        contact.setFirstName("Yewande");
        contact.setLastName("Tinubu");
        contact.setPhoneNumber("08081828183");
        contact.setDateCreated(LocalDate.of(2025, 6, 5));
        contact.setPerson(person2);
        contactRepository.save(contact);

        Contact contact1 = new Contact();
        contact1.setFirstName("Ayo");
        contact1.setPhoneNumber("07081288183");
        contact1.setDateCreated(LocalDate.of(2025, 6, 6));
        contact1.setPerson(person1);
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Tunde");
        contact2.setPhoneNumber("07081828183");
        contact2.setDateCreated(LocalDate.of(2025, 6, 5));
        contact2.setPerson(person2);
        contactRepository.save(contact2);

        List<Contact> getContacts = contactRepository.findByPerson(person1);
        assertEquals(1, getContacts.size());

        List<Contact> getPerson2Contacts = contactRepository.findByPerson(person2);
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

        long numbersSavedOnThe6thOfJune = contactRepository.countContactByDateCreated(LocalDate.of(2025, 6, 6));
        assertEquals(1, numbersSavedOnThe6thOfJune);
    }
}