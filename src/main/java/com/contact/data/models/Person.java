package com.contact.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Person {
    @Id
    private Long personId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
//    @OneToMany(mappedBy = "user")
//    private List<Contact> contacts;
}
