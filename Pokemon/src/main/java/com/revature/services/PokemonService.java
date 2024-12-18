package com.revature.services;

import com.revature.daos.PokemonDao;
import com.revature.models.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    private final PokemonDao pokemonDao;

    @Autowired
    public PokemonService(PokemonDao pokemonDao){
        this.pokemonDao = pokemonDao;
    }

    public Pokemon savePokemon(Pokemon pokemon){
        return pokemonDao.save(pokemon);
    }

    public Optional<Pokemon> findPokemonByName(String name){
        return pokemonDao.findByName(name);
    }

    public Optional<Pokemon> findUserById(int id){
        return pokemonDao.findById(id);
    }

    public List<Pokemon> getAllPokemons(){
        return pokemonDao.findAll();
    }


}
