package com.revature.services;

import com.revature.daos.PokemonDao;
import com.revature.exceptions.pokemon.InvalidPokemonException;
import com.revature.exceptions.pokemon.PokemonIdExistsException;
import com.revature.exceptions.pokemon.PokemonNameExistException;
import com.revature.exceptions.pokemon.PokemonNotFoundException;
import com.revature.models.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonService {
    private final PokemonDao pokemonDao;

    @Autowired
    public PokemonService(PokemonDao pokemonDao){
        this.pokemonDao = pokemonDao;
    }

    public Pokemon savePokemon(Pokemon pokemon) throws PokemonIdExistsException, PokemonNameExistException, InvalidPokemonException {
        if(pokemon == null){
            throw new InvalidPokemonException();
        }

        if(pokemonDao.findById(pokemon.getPokemonId()).isPresent()){
            throw new PokemonIdExistsException();
        }

        if(pokemonDao.findByName(pokemon.getName()).isPresent()){
            throw new PokemonNameExistException();
        }
        return pokemonDao.save(pokemon);
    }

    public Pokemon getPokemonById(int id) throws PokemonNotFoundException {
        Optional<Pokemon> optionalPokemon = pokemonDao.findById(id);
        if(optionalPokemon.isEmpty()){
            throw new PokemonNotFoundException();
        }
        return optionalPokemon.get();
    }

    public List<Pokemon> getAllPokemons() {
        return pokemonDao.findAll();
    }

    public void deletePokemon(int id){
        pokemonDao.deleteById(id);
    }

    public Pokemon editPokemon(Pokemon pokemon) throws InvalidPokemonException, PokemonNotFoundException {
        if(pokemon == null){
            throw new InvalidPokemonException();
        }

        if(pokemonDao.findById(pokemon.getPokemonId()).isEmpty()){
            throw new PokemonNotFoundException();
        }
        return pokemonDao.save(pokemon);
    }

    public Pokemon getPokemonByName(String name) throws PokemonNotFoundException {
        Optional<Pokemon> optionalPokemon = pokemonDao.findByName(name);
        if(optionalPokemon.isEmpty()){
            throw new PokemonNotFoundException();
        }
        return optionalPokemon.get();
    }


    public Set<Pokemon> getFiveRandom(){
        return pokemonDao.findFiveRandom();
    }

    public Set<Pokemon> getPokemonByTypes(List<String> types){
        Set<Pokemon> set = new HashSet<>();

        for(String type : types){
            set.addAll(pokemonDao.findByType(type));
        }
        return set;
    }
}
