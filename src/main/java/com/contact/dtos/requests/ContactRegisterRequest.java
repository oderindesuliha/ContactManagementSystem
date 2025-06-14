package com.contact.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ContactRegisterRequest {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
