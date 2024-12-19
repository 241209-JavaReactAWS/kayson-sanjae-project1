package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UserExistsException;
import com.revature.exceptions.UserNotFoundException;
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

    public User findUserByUsername(String username) throws UserNotFoundException {
        // this may not be needed
        Optional<User> optionalUser = userDAO.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    public User findUserById(int id) throws UserNotFoundException {
        Optional<User> optionalUser = userDAO.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }

        return optionalUser.get();
    }

    public List<User> allUsers(){
        return userDAO.findAll();
    }
}
