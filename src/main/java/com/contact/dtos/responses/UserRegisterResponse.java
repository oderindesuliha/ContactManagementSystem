package com.contact.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserRegisterResponse {
    @Id
    private String userId;
    private String message;

    }

