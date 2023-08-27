package com.example.security.token;

import com.example.security.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
    private static final int EXPIRATION_TIME = 15;

    public VerificationToken(String token, Users user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpiration();
    }

    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpiration();
    }

    private Date getTokenExpiration() {
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(new Date().getTime());
        calender.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calender.getTime().getTime());
    }
}
