package com.revature.controllers;

import com.revature.exceptions.pokemon.PokemonNotFoundException;
import com.revature.exceptions.user.UserNotFoundException;
import com.revature.models.Pokemon;
import com.revature.models.UserPokemon;
import com.revature.services.UserPokemonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("users/pokemons")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class UserPokemonController {
    private final UserPokemonService userPokemonService;

    @Autowired
    public UserPokemonController(UserPokemonService userPokemonService) {
        this.userPokemonService = userPokemonService;
    }

    @PostMapping()
    public ResponseEntity<UserPokemon> addUserPokemon(HttpSession session, @PathVariable("pokemonId") int pokemonId){
        if(session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.badRequest().build();
        }

        try{
            UserPokemon userPokemon = userPokemonService.addUserPokemon((int)session.getAttribute("userId"), pokemonId);
            return ResponseEntity.ok(userPokemon);
        } catch (UserNotFoundException | PokemonNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(params = {"name", "types", "status"})
    public ResponseEntity<List<Pokemon>> getPokemonsByFilters(HttpSession session,
                                                              @RequestParam("name") String pokemonName,
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
            List<Pokemon> pokemons = new ArrayList<>(userPokemonService.
                    getFilterPokemons((int)session.getAttribute("userId"), pokemonName,types, statusInt));
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
