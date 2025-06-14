package com.contact.data.repositories;

import com.contact.data.models.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface Otps extends MongoRepository<Otp, String> {
    Otp findByPhoneNumber(String phoneNumber);
}