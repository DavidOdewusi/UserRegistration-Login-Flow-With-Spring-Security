package com.example.security.event.listener;

import com.example.security.entity.Users;
import com.example.security.event.RegistrationCompleteEvent;
import com.example.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
//        get newly registered user
        Users user = event.getUser();
//        create a verification token for user
        String verificationToken = UUID.randomUUID().toString();
//        save verification token for user
        userService.saveUserVerificationToken(user, verificationToken);
//        build the verification url to be sent to the user
        String url = event.getAppUrl() + "/api/v1/verifyEmail?" + verificationToken;
//        send email to user
        log.info("Click the link to verify: {} ", url);

    }
}
