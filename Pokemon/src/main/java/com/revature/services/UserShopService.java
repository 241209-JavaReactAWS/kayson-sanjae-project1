package com.revature.services;

import com.revature.daos.PokemonDao;
import com.revature.daos.UserShopDAO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Pokemon;
import com.revature.models.UserShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserShopService {
    private final UserShopDAO userShopDAO;
    private final PokemonDao pokemonDao;

    @Autowired
    public UserShopService(UserShopDAO userShopDAO, PokemonDao pokemonDao) {
        this.userShopDAO = userShopDAO;
        this.pokemonDao = pokemonDao;
    }

    public Optional<UserShop> getUserShop(int userId){
        return userShopDAO.findByUserId(userId);
    }

    public List<Pokemon> getUserShopPokemons(int userId) throws UserNotFoundException {
        Optional<UserShop> optionalUserShop = getUserShop(userId);
        if(optionalUserShop.isEmpty()){
            throw new UserNotFoundException();
        }

        UserShop userShop = optionalUserShop.get();
        return  userShop.getAllPokemon();
    }

    public UserShop updateUserShop(int userId) throws UserNotFoundException {
        Optional<UserShop> optionalUserShop = getUserShop(userId);

        if(optionalUserShop.isEmpty()){
            throw new UserNotFoundException();
        }

        UserShop userShop = optionalUserShop.get();
        List<Pokemon> pokemons = pokemonDao.findFiveRandom();
        userShop.setPokemon1(pokemons.get(0));
        userShop.setPokemon2(pokemons.get(1));
        userShop.setPokemon3(pokemons.get(2));
        userShop.setPokemon4(pokemons.get(3));
        userShop.setPokemon5(pokemons.get(4));
        userShopDAO.save(userShop);
        return userShop;
    }
}
