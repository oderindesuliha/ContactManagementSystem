package com.contact.services;

import com.contact.data.repositories.Users;
import com.contact.dtos.requests.UserRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private Users users;

    @BeforeEach
    public void setUp() {
        users.deleteAll();

    }

    @Test
    public void testRegisterUser_RegistrationIsSuccessful() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("Bola");
        registerRequest.setLastName("Oyetola");
        registerRequest.setEmail("b.oyetola@gmail.com");
        registerRequest.setPhoneNumber("09076763421");

        userService.userRegister(registerRequest);
        assertEquals(1, users.count());
    }
}