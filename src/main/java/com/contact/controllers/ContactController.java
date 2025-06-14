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
//    public ResponseEntity<ContactResponse> addContact(@PathVariable String userId, @Valid @RequestBody ContactRequest request) {
//        ContactResponse response = contactService.addContact(userId, request);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<ContactResponse>> getUserContacts(@PathVariable String userId) {
//        List<ContactResponse> contacts = contactService.getUserContacts(userId);
//        return ResponseEntity.ok(contacts);
//    }
//
//    @PostMapping("/{userId}/sync/truecaller")
//    public ResponseEntity<Void> syncTruecaller(@PathVariable String userId) {
//        contactService.syncTruecallerContacts(userId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/{userId}/sync/google")
//    public ResponseEntity<Void> syncGoogleContacts(@PathVariable String userId) {
//        contactService.syncGoogleContacts(userId);
//        return ResponseEntity.ok().build();
//    }
//}