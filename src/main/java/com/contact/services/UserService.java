package com.contact.services;

import com.contact.dtos.requests.UserLoginRequest;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.responses.UserLoginResponse;
import com.contact.dtos.responses.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
    UserLoginResponse login(UserLoginRequest userLoginRequest);

}
