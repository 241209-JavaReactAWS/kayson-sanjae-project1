package com.revature.services;

import com.revature.daos.UserPokemonDAO;
import com.revature.exceptions.UserPokemonNotFoundException;
import com.revature.models.Pokemon;
import com.revature.models.UserPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserPokemonService {
    private final UserPokemonDAO userPokemonDAO;

    @Autowired
    public UserPokemonService(UserPokemonDAO userPokemonDAO) {
        this.userPokemonDAO = userPokemonDAO;
    }

    public Pokemon getPokemonByName(int userId, String name) throws UserPokemonNotFoundException {
        Optional<Pokemon> optionalUserPokemon = userPokemonDAO.findPokemonByName(userId, name);
        if(optionalUserPokemon.isEmpty()){
            throw new UserPokemonNotFoundException();
        }
        return optionalUserPokemon.get();
    }

    public Set<Pokemon> getFilterPokemons(int userId, List<String> types, int status) {
        Set<Pokemon> set = new HashSet<>();
        if (status == 0) {
            set.addAll(userPokemonDAO.findAcquired(userId));
            set.addAll(userPokemonDAO.findUnacquired(userId));
        } else if (status == 1) {
            set.addAll(userPokemonDAO.findAcquired(userId));
        } else if (status == 2) {
            set.addAll(userPokemonDAO.findUnacquired(userId));
        }

        Set<Pokemon> unionOfTypes = new HashSet<>();
        for(String type : types){
            unionOfTypes.addAll(userPokemonDAO.findByType(type));
        }

        set.retainAll(unionOfTypes);
        return set;
    }

}
