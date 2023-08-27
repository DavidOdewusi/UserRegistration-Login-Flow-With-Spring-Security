package com.example.security.controller;

import com.example.security.entity.Users;
import com.example.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("get-all-users")
    public List<Users> getALlUsers() {
        return userService.getAllUsers();
    }


}
