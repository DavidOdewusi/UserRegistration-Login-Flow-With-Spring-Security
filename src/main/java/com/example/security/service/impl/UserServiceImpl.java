package com.example.security.service.impl;

import com.example.security.dto.RegistrationDTO;
import com.example.security.entity.Users;
import com.example.security.exception.UserAlreadyExistException;
import com.example.security.repository.UserRepository;
import com.example.security.repository.VerificationTokenRepository;
import com.example.security.service.UserService;
import com.example.security.token.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users registerUser(RegistrationDTO registrationDTO) {
        Optional<Users> user = this.findUserByEmail(registrationDTO.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("User with email: " + registrationDTO.email() + "already exist");
        }
        var newUser = new Users();
        newUser.setFirstName(registrationDTO.firstName());
        newUser.setLastName(registrationDTO.lastName());
        newUser.setEmail(registrationDTO.email());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.password()));
        newUser.setRole(registrationDTO.role());

        return userRepository.save(newUser);
    }

    @Override
    public Optional<Users> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(Users user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }
}
