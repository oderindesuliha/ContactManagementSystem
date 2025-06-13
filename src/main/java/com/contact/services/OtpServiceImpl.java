package com.contact.services;

import com.contact.data.models.Otp;
import com.contact.data.repositories.Otps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {
    private final Otps otps;

    @Autowired
    public OtpServiceImpl(Otps otps) {
        this.otps = otps;
    }

    @Override
    public String generateOtp(String phoneNumber) {
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String otpCode = "";
        Random random = new Random();

        for (int count = 0; count < 6; count++) {
            otpCode += chars[random.nextInt(6)];

        }

        Otp otp = new Otp();
        otp.setPhoneNumber(phoneNumber);
        otp.setOtpCode(otpCode);
        otp.setCreatedAt(LocalDateTime.now());
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otp.setUsedOtp(false);
        
        otps.save(otp);
        return otpCode;
    }

    @Override
    public boolean isValidOtp(String phoneNumber, String otpCode) {
    Otp otp = otps.findByPhoneNumber(phoneNumber);
    
    if (otp == null) {
        return false;
    }

    if (otp.isUsedOtp() ||
        LocalDateTime.now().isAfter(otp.getExpiresAt()) ||
        !otp.getOtpCode().equals(otpCode)) {
        return false;
    }
    
    otp.setUsedOtp(true);
    otps.save(otp);
    return true;
}
}