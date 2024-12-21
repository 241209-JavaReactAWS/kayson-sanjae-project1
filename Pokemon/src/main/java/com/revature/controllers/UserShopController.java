package com.revature.controllers;

import com.revature.exceptions.user.UserExistsException;
import com.revature.exceptions.user.UserNotFoundException;
import com.revature.models.Pokemon;
import com.revature.models.UserShop;
import com.revature.services.UserShopService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users/shop")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
public class UserShopController {
    private final UserShopService userShopService;

    @Autowired
    public UserShopController(UserShopService userShopService) {
        this.userShopService = userShopService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserShop> addUserShop(HttpSession session){
        if(session.isNew() || session.getAttribute("userId") == null){
            return ResponseEntity.badRequest().build();
        }

        try{
            UserShop newShop = userShopService.addUserShop((int) session.getAttribute("userId"));
            return ResponseEntity.ok(newShop);
        } catch (UserNotFoundException e) {
             return ResponseEntity.status(404).build();
        } catch (UserExistsException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping
    public ResponseEntity<UserShop> getUserShop(HttpSession session){
        if(session.isNew() || session.getAttribute("userId") == null){
            return ResponseEntity.badRequest().build();
        }

        try{
            UserShop userShop = userShopService.getUserShop((int) session.getAttribute("userId"));
            return ResponseEntity.ok(userShop);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/shop-items")
    public ResponseEntity<Set<Pokemon>> getShopPokemons(HttpSession session){
        if(session.isNew() || session.getAttribute("userId") == null){
            return ResponseEntity.badRequest().build();
        }

        try{
            Set<Pokemon> pokemonSet = userShopService.getUserShopPokemons((int) session.getAttribute("userId"));
            return ResponseEntity.ok(pokemonSet);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserShop> updateUserShop(HttpSession session){
        if(session.isNew() || session.getAttribute("userId") == null){
            return ResponseEntity.badRequest().build();
        }

        try{
            UserShop userShop = userShopService.updateUserShop((int) session.getAttribute("userId"));
            return ResponseEntity.ok(userShop);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
