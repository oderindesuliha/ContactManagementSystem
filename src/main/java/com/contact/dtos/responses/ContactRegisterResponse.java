package com.contact.dtos.responses;

import com.contact.data.models.Contact;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class ContactRegisterResponse extends Contact {
    @Id
    private String id;
    private String message;
    private LocalDate dateCreated;
}
