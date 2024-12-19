package com.revature.controllers;

import com.revature.exceptions.UserPokemonNotFoundException;
import com.revature.models.Pokemon;
import com.revature.services.UserPokemonService;
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

    @Autowired
    public UserPokemonController(UserPokemonService userPokemonService) {
        this.userPokemonService = userPokemonService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<Pokemon> getPokemonByName(HttpSession session, @PathVariable("userId") int userId, @RequestParam("name") String pokemonName){
        if(session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.badRequest().build();
        }

        try{
            Pokemon pokemon = userPokemonService.getPokemonByName(userId, pokemonName);
            return ResponseEntity.ok(pokemon);
        }catch(UserPokemonNotFoundException e){
            return ResponseEntity.notFound().build();
        }
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

        if(statusInt == -1){
            return ResponseEntity.internalServerError().build();
        }

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
