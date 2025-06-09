package com.contact.data.models;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name ="contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateCreated;
    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;
}
