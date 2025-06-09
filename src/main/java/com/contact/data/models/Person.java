package com.contact.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
//    @OneToMany(mappedBy = "user")
//    private List<Contact> contacts;
}
