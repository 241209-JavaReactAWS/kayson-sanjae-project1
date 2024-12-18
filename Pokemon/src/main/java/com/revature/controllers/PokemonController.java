package com.revature.controllers;

import com.revature.models.Pokemon;
import com.revature.services.PokemonService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
