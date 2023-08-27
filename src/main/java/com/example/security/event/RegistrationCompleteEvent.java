package com.example.security.event;

import com.example.security.entity.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Users user;
    private String appUrl;

    public RegistrationCompleteEvent(Users user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }
}
