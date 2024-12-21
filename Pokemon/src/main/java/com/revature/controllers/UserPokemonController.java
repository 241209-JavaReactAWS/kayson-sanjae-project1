package com.revature.controllers;

import com.revature.exceptions.pokemon.PokemonNotFoundException;
import com.revature.exceptions.user_shop.UserPokemonNotFoundException;
import com.revature.models.Pokemon;
import com.revature.services.PokemonService;
import com.revature.services.UserPokemonService;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("users/{userId}/pokemons")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class UserPokemonController {
    private final UserPokemonService userPokemonService;
    private final PokemonService pokemonService;
    private final UserService userService;

    @Autowired
    public UserPokemonController(UserPokemonService userPokemonService, PokemonService pokemonService, UserService userService) {
        this.userPokemonService = userPokemonService;
        this.pokemonService = pokemonService;
        this.userService = userService;
    }

    @GetMapping(params = {"types", "status"})
    public ResponseEntity<List<Pokemon>> getPokemonsByFilters(HttpSession session,
                                                              @PathVariable("userId") int userId,
                                                              @RequestParam("pokemonname") String pokemonName,
                                                              @RequestParam("types") List<String> types,
                                                              @RequestParam("status") String status){
        if(session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.badRequest().build();
        }

        int statusInt = mapStatusToInt(status);

        if(statusInt == -1){
            return ResponseEntity.internalServerError().build();
        }

        try{
            List<Pokemon> pokemons = new ArrayList<>(userPokemonService.getFilterPokemons(userId, pokemonName,types, statusInt));
            pokemons.sort(Comparator.comparingInt(Pokemon::getPokemonId));
            return ResponseEntity.ok(pokemons);
        }catch(PokemonNotFoundException e){
            return ResponseEntity.status(404).build();
        }

    }

    private int mapStatusToInt(String status){
        return switch (status.toLowerCase()) {
            case "acquired" -> 0;
            case "unacquired" -> 1;
            case "acquired&unacquired" -> 2;
            default -> -1;
        };
    }
}
