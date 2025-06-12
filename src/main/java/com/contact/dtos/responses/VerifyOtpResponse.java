package com.contact.dtos.responses;


import lombok.Data;

@Data
public class VerifyOtpResponse {
    private boolean validOtp;
    private String message;
}
