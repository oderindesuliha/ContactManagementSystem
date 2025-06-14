package com.contact.dtos.responses;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class VerifyOtpResponse {
    private boolean validOtp;
    private String message;
}
