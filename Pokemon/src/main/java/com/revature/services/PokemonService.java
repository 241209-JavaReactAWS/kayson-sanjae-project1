package com.revature.services;

import com.revature.daos.PokemonDao;
import com.revature.exceptions.PokemonIdExistsException;
import com.revature.exceptions.PokemonNameExistException;
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

    public Pokemon savePokemon(Pokemon pokemon) throws PokemonIdExistsException, PokemonNameExistException {
        if(pokemonDao.findById(pokemon.getPokemonId()).isPresent()){
            throw new PokemonIdExistsException();
        }

        if(pokemonDao.findByName(pokemon.getName()).isPresent()){
            throw new PokemonNameExistException();
        }
        return pokemonDao.save(pokemon);
    }

    public Optional<Pokemon> findPokemonByName(String name){
        return pokemonDao.findByName(name);
    }

    public Optional<Pokemon> findPokemonById(int id){
        return pokemonDao.findById(id);
    }

    public List<Pokemon> getAllPokemons(){
        return pokemonDao.findAll();
    }

    public void deletePokemon(int id){
        pokemonDao.deleteById(id);
    }
}
