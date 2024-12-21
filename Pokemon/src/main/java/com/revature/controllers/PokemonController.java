package com.revature.controllers;

import com.revature.exceptions.InvalidPokemonException;
import com.revature.models.Pokemon;
import com.revature.services.PokemonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pokemons")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
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

    @GetMapping("/{pokemonId}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable int pokemonId){
        try{
            Pokemon pokemon = pokemonService.getPokemonById(pokemonId);
            return ResponseEntity.ok(pokemon);
        }catch(InvalidPokemonException e){
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping()
    public ResponseEntity<Pokemon> getPokemonByName(@RequestParam String name){
        try{
            Pokemon pokemon = pokemonService.getPokemonByName(name);
            return ResponseEntity.status(302).header("Location", "/" + pokemon.getPokemonId())
                    .body(pokemon);
        }catch (InvalidPokemonException e){
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Pokemon>> getAllPokemon(){
        return ResponseEntity.ok(pokemonService.getAllPokemons());
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<?> deletePokemonById(@PathVariable int pokemonId){
        try{
            Pokemon pokemon = pokemonService.getPokemonById(pokemonId);
            pokemonService.deletePokemon(pokemonId);
        }catch (InvalidPokemonException e){}
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<Pokemon> addNewPokemon(HttpSession session, @RequestBody Pokemon pokemon){
        try{
            Pokemon returnedPokemon = pokemonService.savePokemon(pokemon);
            return ResponseEntity.status(201).body(returnedPokemon);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Pokemon> editPokemon(HttpSession session, @RequestBody Pokemon pokemon){
        try{
            Pokemon returnedPokemon = pokemonService.editPokemon(pokemon);
            return ResponseEntity.status(200).body(returnedPokemon);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
