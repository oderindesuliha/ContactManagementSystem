package com.contact.services;

import com.contact.data.models.User;
import com.contact.data.repositories.Users;
import com.contact.dtos.requests.OtpRequest;
import com.contact.dtos.requests.UserLoginRequest;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.OtpResponse;
import com.contact.dtos.responses.UserLoginResponse;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import validations.UserValidation;

import java.util.Random;
=======
import validations.UserValidations;
>>>>>>> 1224300d58742ec5f588f63f7945e2742cbb368b

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Users users;

    @Override
<<<<<<< HEAD
    public UserRegisterResponse register(UserRegisterRequest request) {
        UserRegisterResponse response = new UserRegisterResponse();

        if (!UserValidation.validateUser(request)) {
            response.setMessage("Invalid user details");
            return response;
        }
        if (request.getOtpCode() == null || request.getOtpCode().isEmpty()) {
            response.setMessage("OTP required");
            return response;
        }
        if (users.existsByEmail(request.getEmail())) {
            response.setMessage("Email already exists");
            return response;
        }

        User user = users.findByPhoneNumber(request.getPhoneNumber());
        if (user == null || !user.getOtpCode().equals(request.getOtpCode())) {
            response.setMessage("Invalid or already used OTP");
            return response;
        }
        if (user.isVerified()) {
            response.setMessage("Phone number already registered");
            return response;
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setVerified(true);
        users.save(user);

        response.setMessage("User registered successfully");
=======
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
>>>>>>> 1224300d58742ec5f588f63f7945e2742cbb368b
        return response;
    }

    @Override
<<<<<<< HEAD
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();

        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            response.setMessage("Invalid email, password, or unverified account");
            return response;
        }

        User user = users.findByEmail(request.getEmail());
        if (user == null || !user.getPassword().equals(request.getPassword()) || !user.isVerified()) {
            response.setMessage("Invalid email, password, or unverified account");
            return response;
        }

        response.setMessage("Login successful");
        return response;
=======
    public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) {
        return null;
>>>>>>> 1224300d58742ec5f588f63f7945e2742cbb368b
    }

    @Override
    public OtpResponse generateOtp(OtpRequest request) {
        OtpResponse response = new OtpResponse();

        if (users.existsByPhoneNumber(request.getPhoneNumber())) {
            User existingUser = users.findByPhoneNumber(request.getPhoneNumber());
            if (existingUser.isVerified()) {
                response.setValidPhoneNumber(false);
                response.setMessage("Phone number already registered");
                return response;
            }
        }

        char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        String otpCode = "";
        Random random = new Random();
        for (int count = 0; count < 6; count++) {
            otpCode += chars[random.nextInt(chars.length)];
        }

        User user = users.findByPhoneNumber(request.getPhoneNumber());
        if (user == null) {
            user = new User();
            user.setPhoneNumber(request.getPhoneNumber());
        }
        user.setOtpCode(otpCode);
        user.setVerified(false);
        users.save(user);

        response.setValidPhoneNumber(true);
        response.setMessage("OTP generated successfully");
        return response;
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request) {
        VerifyOtpResponse response = new VerifyOtpResponse();

        if (request == null || request.getPhoneNumber() == null || request.getOtpCode() == null) {
            response.setValidOtp(false);
            response.setMessage("Invalid or already used OTP");
            return response;
        }

        User user = users.findByPhoneNumber(request.getPhoneNumber());
        if (user == null || !user.getOtpCode().equals(request.getOtpCode()) || user.isVerified()) {
            response.setValidOtp(false);
            response.setMessage("Invalid or already used OTP");
            return response;
        }

        user.setVerified(true);
        users.save(user);

        response.setValidOtp(true);
        response.setMessage("OTP verified successfully");
        return response;
    }
}



