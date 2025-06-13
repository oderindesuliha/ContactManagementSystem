package com.contact.services;

import com.contact.data.models.Otp;
import com.contact.data.models.User;
import com.contact.data.repositories.Otps;
import com.contact.data.repositories.Users;
import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.dtos.requests.VerifyOtpRequest;
import com.contact.dtos.responses.UserRegisterResponse;
import com.contact.dtos.responses.VerifyOtpResponse;
import com.contact.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private Users users;

    @Autowired
    private Otps otps;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        User user = new User();
    }

    @Test
    public void testRegisterUser_RegisterationSuccessful() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setFirstName("Bola");
        request.setLastName("Oyetola");
        request.setEmail("b.oyetola@gmail.com");
        request.setPhoneNumber("09076763421");

        UserRegisterResponse response = userService.register(request);
        assertNotNull(response);
        assertEquals("Account created successfully...OTP has been sent for verification", response.getMessage());
        assertNotNull(response.getUserId());
        assertEquals(1, users.count());
    }

    @Test
    public void testRegisterUserPhoneNumberwith_234_Registerationsuccessful() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setFirstName("Tola");
        request.setLastName("Adeniyi");
        request.setEmail("t.ade@gmail.com");
        request.setPhoneNumber("+2349075363421");

        UserRegisterResponse response = userService.register(request);
        assertNotNull(response);
        assertEquals("Account created successfully...OTP has been sent for verification", response.getMessage());
        assertNotNull(response.getUserId());
        assertEquals(1, users.count());
    }

    @Test
    public void testToRegisterUser_GetAndVerifyOtp_VerificationSuccessful() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("Bola");
        registerRequest.setLastName("Oyetola");
        registerRequest.setEmail("b.oyetola@gmail.com");
        registerRequest.setPhoneNumber("09076763421");
        userService.register(registerRequest);

        String otpCode = otps.findByPhoneNumber("09076763421").getOtpCode();

        VerifyOtpRequest verifyRequest = new VerifyOtpRequest();
        verifyRequest.setPhoneNumber("09076763421");
        verifyRequest.setOtpCode(otpCode);

        VerifyOtpResponse response = userService.verifyOtp(verifyRequest);
        assertTrue(response.isValidOtp());
        assertEquals("OTP verified successfully", response.getMessage());
    }

    @Test
    public void testVerifyOtp_alreadyVerified() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("Bola");
        registerRequest.setLastName("Oyetola");
        registerRequest.setEmail("b.oyetola@gmail.com");
        registerRequest.setPhoneNumber("09076763421");
        userService.register(registerRequest);

        String otpCode = otps.findByPhoneNumber("09076763421").getOtpCode();

        VerifyOtpRequest verifyRequest = new VerifyOtpRequest();
        verifyRequest.setPhoneNumber("09076763421");
        verifyRequest.setOtpCode(otpCode);
        userService.verifyOtp(verifyRequest);

        VerifyOtpResponse response = userService.verifyOtp(verifyRequest);
        assertTrue(response.isValidOtp());
        assertEquals("User already verified", response.getMessage());
    }

    @Test
    public void testToRegisterUser_SendOtp_ReturnCount() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("Bola");
        registerRequest.setLastName("Oyetola");
        registerRequest.setEmail("b.oyetola@gmail.com");
        registerRequest.setPhoneNumber("09076763421");
        userService.register(registerRequest);

        userService.sendOtp("09076763421");
        assertEquals(2, otps.count());
    }

    @Test
    public void testRegisterUser_duplicatePhoneNumber_throwsException() {
        UserRegisterRequest request1 = new UserRegisterRequest();
        request1.setFirstName("Bola");
        request1.setLastName("Oyetola");
        request1.setEmail("b.oyetola@gmail.com");
        request1.setPhoneNumber("09076763421");
        userService.register(request1);

        UserRegisterRequest request2 = new UserRegisterRequest();
        request2.setFirstName("Tola");
        request2.setLastName("Ade");
        request2.setEmail("t.ade@gmail.com");
        request2.setPhoneNumber("09076763421");

        assertThrows(UserException.class, () -> userService.register(request2), "User with this phone number already exists");
    }

    @Test
    public void testRegisterUser_duplicateEmail_throwsException() {
        UserRegisterRequest request1 = new UserRegisterRequest();
        request1.setFirstName("Bola");
        request1.setLastName("Oyetola");
        request1.setEmail("b.oyetola@gmail.com");
        request1.setPhoneNumber("09076763421");
        userService.register(request1);

        UserRegisterRequest request2 = new UserRegisterRequest();
        request2.setFirstName("Tola");
        request2.setLastName("Ade");
        request2.setEmail("b.oyetola@gmail.com");
        request2.setPhoneNumber("08123456789");

        assertThrows(UserException.class, () -> userService.register(request2), "Email already in use");
    }

    @Test
    public void testRegisterUser_invalidEmail_throwsException() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setFirstName("Bola");
        request.setLastName("Oyetola");
        request.setEmail("invalid-email");
        request.setPhoneNumber("09076763421");

        assertThrows(UserException.class, () -> userService.register(request), "Email is required.....Enter a valid email address");
    }

    @Test
    public void testRegisterUser_invalidPhoneNumber_throwsException() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setFirstName("Bola");
        request.setLastName("Oyetola");
        request.setEmail("b.oyetola@gmail.com");
        request.setPhoneNumber("1234567890");

        assertThrows(UserException.class, () -> userService.register(request), "Phone number must start with either 0 or +234");
    }

    @Test
    public void testToRegisterUser_VerifyOtp_invalidOtp_throwsException() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("Bola");
        registerRequest.setLastName("Oyetola");
        registerRequest.setEmail("b.oyetola@gmail.com");
        registerRequest.setPhoneNumber("09076763421");
        userService.register(registerRequest);

        VerifyOtpRequest verifyRequest = new VerifyOtpRequest();
        verifyRequest.setPhoneNumber("09076763421");
        verifyRequest.setOtpCode("999999");

        VerifyOtpResponse response = userService.verifyOtp(verifyRequest);
        assertFalse(response.isValidOtp());
        assertEquals("Invalid OTP", response.getMessage());
    }

    @Test
    public void testSendOtp_userNotFound_throwsException() {
        assertThrows(UserException.class, () -> userService.sendOtp("09076763421"), "User not found");
    }

    @Test
    public void testVerifyOtp_nullRequest_throwsException() {
        assertThrows(UserException.class, () -> userService.verifyOtp(null), "Invalid verification");
    }
}