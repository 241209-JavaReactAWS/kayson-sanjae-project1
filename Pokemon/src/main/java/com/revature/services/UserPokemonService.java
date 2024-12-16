package com.revature.services;

import com.revature.daos.UserPokemonDAO;
import com.revature.models.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {
    private final UserPokemonDAO userPokemonDAO;

    @Autowired
    public UserPokemonService(UserPokemonDAO userPokemonDAO) {
        this.userPokemonDAO = userPokemonDAO;
    }

    public List<Pokemon> getAllPokemonByUserID(int userId){
        return userPokemonDAO.findAllByUserId(userId);
    }

    public List<Pokemon> getAllUnacquiredPokemonByUserId(int userId){
        return userPokemonDAO.findAllNotAcquiredByUser(userId);
    }
}
