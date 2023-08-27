package com.example.security.service;

import com.example.security.dto.RegistrationDTO;
import com.example.security.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<Users> getAllUsers();
    Users registerUser(RegistrationDTO registrationDTO);
    Optional<Users> findUserByEmail(String email);

    void saveUserVerificationToken(Users user, String token);
}
