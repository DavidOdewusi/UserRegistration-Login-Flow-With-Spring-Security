package com.example.security.dto;

import lombok.*;
import org.hibernate.annotations.NaturalId;

public record RegistrationDTO(
        String firstName,
        String lastName,
        String password,
        String email,
        String role) {

}
