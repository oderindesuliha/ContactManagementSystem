package com.contact.services;

import com.contact.data.repositories.Users;
import com.contact.dtos.requests.UserLoginRequest;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.UserLoginResponse;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private Users users;
    private UserRegisterRequest registerRequest;


    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        return null;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return null;
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        return null;
    }
}
