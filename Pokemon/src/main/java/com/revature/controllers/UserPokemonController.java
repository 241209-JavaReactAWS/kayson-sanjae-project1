package com.revature.controllers;

import com.revature.services.UserPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/{userId}/pokemons")
public class UserPokemonController {
    private final UserPokemonService userPokemonService;

    @Autowired
    public UserPokemonController(UserPokemonService userPokemonService) {
        this.userPokemonService = userPokemonService;
    }
}
