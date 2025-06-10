package com.contact.dtos.responses;

import lombok.Data;

@Data
public class OtpResponse {
    private String validNumber;
    private String message;
}
