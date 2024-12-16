package com.revature.controllers;

import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
public class Controllers {
    private final UserService userService;
    @Autowired
    public Controllers(UserService userService){
        this.userService = userService;
    }
}
