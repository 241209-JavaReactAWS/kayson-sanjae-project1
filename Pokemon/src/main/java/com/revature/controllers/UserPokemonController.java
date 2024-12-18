package com.revature.controllers;

import com.revature.models.Pokemon;
import com.revature.services.UserPokemonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("user/{userId}/pokemons")
public class UserPokemonController {
    private final UserPokemonService userPokemonService;

    @Autowired
    public UserPokemonController(UserPokemonService userPokemonService) {
        this.userPokemonService = userPokemonService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<Pokemon> getPokemonByName(HttpSession session, @PathVariable("userId") int userId, @RequestParam("name") String pokemonName){
        if(session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.badRequest().build();
        }


        Optional<Pokemon> optionalPokemon = userPokemonService.getPokemonByName(userId, pokemonName);
        return optionalPokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());

    }

    @GetMapping(params = {"types", "status"})
    public ResponseEntity<List<Pokemon>> getPokemonsByFilters(HttpSession session,
                                                              @PathVariable("userId") int userId,
                                                              @RequestParam("types") List<String> types,
                                                              @RequestParam("status") String status){
        if(session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.badRequest().build();
        }

        int statusInt = mapStatusToInt(status);
        List<Pokemon> pokemons = new ArrayList<>(userPokemonService.getFilterPokemons(userId, types, statusInt));
        pokemons.sort(Comparator.comparingInt(Pokemon::getPokemonId));
        return ResponseEntity.ok(pokemons);
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
