package com.revature.controllers;

import com.revature.exceptions.user.InvalidCredentialsException;
import com.revature.exceptions.user.UserExistsException;
import com.revature.exceptions.user.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<User> getUserInfoHandler(HttpSession session) {
        if (session.isNew() || session.getAttribute("userId") == null){
            return ResponseEntity.status(401).build();
        }

        try{
            User userToBeReturned = userService.getUserById((int)session.getAttribute("userId"));
            return ResponseEntity.status(302).header("Location", "/" + userToBeReturned.getUserId())
                    .body(userToBeReturned);
        }catch (UserNotFoundException e){
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping()
    public ResponseEntity<User> getUserByUserName(HttpSession session) {
        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build();
        }

        try{
            User userToBeReturned = userService.getUserById((int)session.getAttribute("username"));
            return ResponseEntity.status(302).header("Location", "/" + userToBeReturned.getUserId())
                    .body(userToBeReturned);
        }catch(UserNotFoundException e){
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginHandler(@RequestBody User user, HttpSession session) {
        try {
            User returningUser = userService.loginUser(user.getUsername(), user.getPassword());
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

    @GetMapping()
    public ResponseEntity<List<User>> getALlUser(HttpSession session){
        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build();
        }
        List<User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}


