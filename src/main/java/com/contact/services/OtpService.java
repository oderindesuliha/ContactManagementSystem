package com.contact.services;


public interface OtpService {
    String generateOtp(String phoneNumber);
    boolean isValidOtp(String phoneNumber, String otpCode);

}
