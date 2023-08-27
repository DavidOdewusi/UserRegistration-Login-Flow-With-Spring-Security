package com.example.security.controller;

import com.example.security.dto.RegistrationDTO;
import com.example.security.entity.Users;
import com.example.security.event.RegistrationCompleteEvent;
import com.example.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;


    @PostMapping("register")
    public String registerUser(@RequestBody RegistrationDTO registrationDTO, final HttpServletRequest request) {
        Users user = userService.registerUser(registrationDTO);
//        publish registration event
        eventPublisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Registered Successfully, Please check your email for verification";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
