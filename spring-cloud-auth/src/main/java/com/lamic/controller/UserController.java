package com.lamic.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	//@RequestMapping("/user")
	@GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}