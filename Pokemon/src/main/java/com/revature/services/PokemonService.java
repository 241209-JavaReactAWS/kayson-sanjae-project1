package com.revature.services;

import com.revature.daos.PokemonDao;
import com.revature.exceptions.InvalidPokemonException;
import com.revature.exceptions.PokemonIdExistsException;
import com.revature.exceptions.PokemonNameExistException;
import com.revature.models.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Pokemon getPokemonById(int id) throws InvalidPokemonException {
        Optional<Pokemon> optionalPokemon = pokemonDao.findById(id);
        if(optionalPokemon.isEmpty()){
            throw new InvalidPokemonException();
        }
        return optionalPokemon.get();
    }

    public List<Pokemon> getAllPokemons() {
        return pokemonDao.findAll();
    }

    public void deletePokemon(int id){
        pokemonDao.deleteById(id);
    }

    public Pokemon editPokemon(Pokemon pokemon) throws InvalidPokemonException {
        if(pokemon == null || pokemonDao.findById(pokemon.getPokemonId()).isEmpty()){
            throw new InvalidPokemonException();
        }
        return pokemonDao.save(pokemon);
    }

    public Pokemon getPokemonByName(String name) throws InvalidPokemonException {
        Optional<Pokemon> optionalPokemon = pokemonDao.findByName(name);
        if(optionalPokemon.isEmpty()){
            throw new InvalidPokemonException();
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
