package com.revature.services;

import com.revature.daos.UserDAO;
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


    public User saveUser(User user){
        return userDAO.save(user);
    }

    public Optional<User> findUserByUsername(String username){
        return userDAO.findByUsername(username);
    }

    public Optional<User> findUserById(int id){
        return userDAO.findById(id);
    }

    public List<User> allUsers(){
        return userDAO.findAll();
    }
}
