package com.revature.services;

import com.revature.daos.PokemonDao;
import com.revature.daos.UserShopDAO;
import com.revature.exceptions.user.UserNotFoundException;
import com.revature.models.Pokemon;
import com.revature.models.UserShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserShop getUserShop(int userId) throws UserNotFoundException {
        Optional<UserShop> optionalUserShop = userShopDAO.findByUserId(userId);
        if(optionalUserShop.isEmpty()){
            throw new UserNotFoundException();
        }
        return optionalUserShop.get();
    }

    public List<Pokemon> getUserShopPokemons(int userId) throws UserNotFoundException {
        return getUserShop(userId).getAllPokemon();
    }

    public UserShop updateUserShop(int userId) throws UserNotFoundException {
        UserShop userShop = getUserShop(userId);
        /*
        List<Pokemon> pokemons = pokemonDao.findFiveRandom();
        userShop.setAllPokemon(pokemons);
        userShopDAO.save(userShop);
        */
        return userShop;
    }
}
