package com.revature.controllers;

import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UserExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerHandler(@RequestBody User user){
        try{
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(user);
        }catch(UserExistsException e){
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping
    public ResponseEntity<User> getUserInfoHandler(HttpSession session) throws UserNotFoundException {
        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build();
        }
        User userToBeReturned = userService.findUserByUsername( (String) session.getAttribute("username"));

        return ResponseEntity.ok(userToBeReturned);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginHandler(@RequestBody User user, HttpSession session) {
        try {
            User returningUser = userService.loginUser(user.getUsername(), user.getPassword());
            //todo: add a login bonus
            // if last login date is the previous day we add coins and then update last login
            session.setAttribute("username", returningUser.getUsername());
            session.setAttribute("userId", returningUser.getUserId());
            session.setAttribute("role", returningUser.getRole());
            return ResponseEntity.ok(returningUser);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutHandler(HttpSession session){
        session.invalidate();
        return ResponseEntity.noContent().build();
    }
}


