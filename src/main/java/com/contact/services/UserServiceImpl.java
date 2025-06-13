package com.contact.services;

import com.contact.data.models.User;
import com.contact.data.repositories.Users;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;
import com.contact.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validations.UserValidation;

@Service
public class UserServiceImpl implements UserService {
    private final Users users;
    private final OtpService otpService;

    @Autowired
    public UserServiceImpl(Users users, OtpService otpService) {
        this.users = users;
        this.otpService = otpService;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        UserValidation.validateUser(request);

        if (users.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new UserException("User with this phone number already exists");
        }

        if (users.existsByEmail(request.getEmail())) {
            throw new UserException("Email already in use");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setVerified(false);

        User savedUser = users.save(user);
        String otp = otpService.generateOtp(request.getPhoneNumber());

        UserRegisterResponse response = new UserRegisterResponse();
        response.setMessage("Account created successfully...OTP has been sent for verification");
        response.setUserId(savedUser.getUserId());
        return response;
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        if (verifyOtpRequest == null || verifyOtpRequest.getPhoneNumber() == null || verifyOtpRequest.getOtpCode() == null) {
            throw new UserException("Invalid verification");
        }

        User user = users.findByPhoneNumber(verifyOtpRequest.getPhoneNumber());
        if (user == null) {
            throw new UserException("User not found");
        }

        VerifyOtpResponse response = new VerifyOtpResponse();

        if (user.isVerified()) {
            response.setValidOtp(true);
            response.setMessage("User already verified");
            return response;
        }

        boolean isOtpValid = otpService.isValidOtp(verifyOtpRequest.getPhoneNumber(), verifyOtpRequest.getOtpCode());

        if (!isOtpValid) {
            response.setValidOtp(false);
            response.setMessage("Invalid OTP");
            return response;
        }

        user.setVerified(true);
        users.save(user);

        response.setValidOtp(true);
        response.setMessage("OTP verified successfully");
        return response;
    }

    @Override
    public void sendOtp(String phoneNumber) {
        User user = users.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UserException("User not found");
        }
        otpService.generateOtp(phoneNumber);
    }
}