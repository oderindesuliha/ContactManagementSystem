package com.contact.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class VerifyOtpRequest {
    private String phoneNumber;
    private String otpCode;


}
