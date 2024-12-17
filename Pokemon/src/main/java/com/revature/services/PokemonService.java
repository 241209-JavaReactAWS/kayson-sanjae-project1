package com.revature.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.daos.PokemonDao;
import com.revature.exceptions.InvalidPokemonException;
import com.revature.models.Pokemon;
import com.revature.models.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    private final PokemonDao pokemonDao;
    private final RestTemplate restTemplate;

    @Autowired
    public PokemonService(PokemonDao pokemonDao, RestTemplate restTemplate) {

        this.pokemonDao = pokemonDao;
        this.restTemplate = restTemplate;
    }

    public Pokemon savePokemon(Pokemon pokemon){
        return pokemonDao.save(pokemon);
    }

    public Pokemon savePokemonByName(String name) throws InvalidPokemonException {
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try{
            JsonNode map = new ObjectMapper().readTree(response.getBody());
            Pokemon pokemon = new Pokemon();
            pokemon.setName(map.get("name").asText());
            PokemonType type1 = PokemonType.fromString(map.get("types").get(0).get("type").get("name").asText());
            pokemon.setType1(type1);


            // if pokemon only has 1 type
            PokemonType type2 = null;
            if(map.get("types").size() > 1){
                type2 = PokemonType.fromString(map.get("types").get(1).get("type").get("name").asText());
            }

            pokemon.setImgUrl(map.get("sprites").get("front_default").asText());
            return savePokemon(pokemon);
        }catch (Exception e){
            throw new InvalidPokemonException();
        }
    }

    public Optional<Pokemon> findPokemonByName(String name){
        return pokemonDao.findByName(name);
    }

    public Optional<Pokemon> findUserById(int id){
        return pokemonDao.findById(id);
    }

    public List<Pokemon> allPokemons(){
        return pokemonDao.findAll();
    }


}
