package com.contact.services;

import com.contact.dtos.requests.OtpRequest;
import com.contact.dtos.requests.UserLoginRequest;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.OtpResponse;
import com.contact.dtos.responses.UserLoginResponse;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;

public interface UserService {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
    UserLoginResponse login(UserLoginRequest userLoginRequest);
    OtpResponse generateOtp(OtpRequest otpRequest);
    VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);

}
