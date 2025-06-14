//package com.contact.controllers;
//
//import com.contact.services.ContactService;
//import com.contact.validations.ContactValidations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/contacts")
//public class ContactController {
//    private final ContactService contactService;
//
//    @Autowired
//    public ContactController(ContactService contactService) {
//        this.contactService = contactService;
//    }
//
//    @PostMapping("/{userId}")
//    public ResponseEntity<ContactResponse> addContact( @RequestBody ContactRequest request) {
//        ContactResponse response = contactService.addContact(userId, request);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<ContactResponse>> getUserContacts() {
//        List<ContactResponse> contacts = contactService.getUserContacts(userId);
//        return ResponseEntity.ok(contacts);
//    }
//