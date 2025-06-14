//package com.contact.controllers;
//
//import com.contact.dtos.requests.UserRegisterRequest;
//import com.contact.dtos.requests.VerifyOtpRequest;
//import com.contact.dtos.responses.UserRegisterResponse;
//import com.contact.dtos.responses.VerifyOtpResponse;
//import com.contact.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request) {
//        UserRegisterResponse response = userService.register(request);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/otp/send")
//    public ResponseEntity<Void> sendOtp(@RequestParam String phoneNumber) {
//        userService.sendOtp(phoneNumber);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/otp/verify")
//    public ResponseEntity<VerifyOtpResponse> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
//        VerifyOtpResponse response = userService.verifyOtp(request);
//        return ResponseEntity.ok(response);
//    }
//}