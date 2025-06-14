package com.contact.services;

import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;

public interface UserService  {
    UserRegisterResponse register(UserRegisterRequest request);
    VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);
    void sendOtp(String phoneNumber);
}

