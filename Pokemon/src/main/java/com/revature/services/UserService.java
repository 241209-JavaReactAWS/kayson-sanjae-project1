package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.exceptions.pokemon.InvalidPokemonException;
import com.revature.exceptions.pokemon.PokemonNotFoundException;
import com.revature.exceptions.user.InvalidCredentialsException;
import com.revature.exceptions.user.UserExistsException;
import com.revature.exceptions.user.UserNotFoundException;
import com.revature.models.Pokemon;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User registerUser(User user) throws UserExistsException {
        Optional<User> optionalUser = userDAO.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            throw new UserExistsException();
        }
        // todo: password specifications
        return userDAO.save(user);
    }

    public User loginUser(String username, String password) throws InvalidCredentialsException {
        Optional<User> optionalUser = userDAO.findByCredentials(username, password);
        if(optionalUser.isEmpty()){
            throw new InvalidCredentialsException();
        }
        return optionalUser.get();
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        Optional<User> optionalUser = userDAO.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    public User getUserById(int id) throws UserNotFoundException {
        Optional<User> optionalUser = userDAO.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    public List<User> findAllUsers(){
        return userDAO.findAll();
    }

    public void deleteUser(int id){
        userDAO.deleteById(id);
    }

    public User editUser(User user) throws UserNotFoundException {
        if(user == null){
            throw new UserNotFoundException();
        }

        if(userDAO.findById(user.getUserId()).isEmpty()){
            throw new UserNotFoundException();
        }
        return userDAO.save(user);
    }
}
