package com.contact.services;

import com.contact.data.models.User;
import com.contact.data.repositories.Users;
import com.contact.dtos.requests.UserLoginRequest;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.UserLoginResponse;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;
import org.springframework.stereotype.Service;
import validations.UserValidations;

@Service
public class UserServiceImpl implements UserService {

    private Users users;
    private UserRegisterRequest registerRequest;


    @Override
    public UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest) {

        UserValidations.validateUser(userRegisterRequest);


        User user = new User();
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPhoneNumber(userRegisterRequest.getPhoneNumber());

        users.save(user);

        UserRegisterResponse response = new UserRegisterResponse();
        response.setSucc(true);
        response.setMessage("User registered successfully");
        response.setEmail(user.getEmail());
        return response;
    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) {
        return null;
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        return null;
    }
}
