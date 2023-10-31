package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.User;
import com.app.payloads.UserRegisterPayload;
import com.app.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	private UserService userService;
	
	@Autowired
	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/users")
    public User createUser(@RequestBody UserRegisterPayload userPayload) {
        return userService.createUser(userPayload.getUsername(), userPayload.getPassword(), userPayload.getEmail());
    }
}
