package com.revature.controllers;

import com.revature.models.Pokemon;
import com.revature.services.PokemonService;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pokemons")
public class PokemonController {
    private final PokemonService pokemonService;
    @Autowired
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemons(){
        return pokemonService.getAllPokemons();
    }

    @GetMapping("id/{pokemonId}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable int pokemonId){
        Optional<Pokemon> returnedPokemon_opt = pokemonService.findPokemonById(pokemonId);

        if(returnedPokemon_opt.isPresent()){
            return ResponseEntity.status(200).body(returnedPokemon_opt.get());
        }else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("id/{pokemonId}")
    public ResponseEntity<Pokemon> deletePokemonById(@PathVariable int pokemonId){
        Optional<Pokemon> returnedPokemon_opt = pokemonService.findPokemonById(pokemonId);

        if(returnedPokemon_opt.isPresent()){
            pokemonService.deletePokemon(pokemonId);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Pokemon> getPokemonByName(@PathVariable String name){
        Optional<Pokemon> returnedPokemon_opt = pokemonService.findPokemonByName(name);

        if(returnedPokemon_opt.isPresent()){
            return ResponseEntity.status(200).body(returnedPokemon_opt.get());
        }else {
            return ResponseEntity.status(404).build();
        }
    }



    @PostMapping
    public ResponseEntity<Pokemon> addNewPokemon(HttpSession session, @RequestBody Pokemon pokemon){
        //todo: no duplicated pokemon name
        Pokemon returnedPokemon = pokemonService.savePokemon(pokemon);

        if(returnedPokemon == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(returnedPokemon);
    }



}
