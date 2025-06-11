package com.contact.services;

import com.contact.dtos.requests.UserLoginRequest;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.UserLoginResponse;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;

public interface UserService {
    UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest);
    UserLoginResponse userLogin(UserLoginRequest userLoginRequest);
    VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);

}
