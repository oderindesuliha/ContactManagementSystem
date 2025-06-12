package com.contact.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String otpCode;
    private boolean OtpUsed;
    private boolean verified;

}
