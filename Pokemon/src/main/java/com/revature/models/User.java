package com.revature.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(unique = true)
    private String username;
    private String password;
    private Role role;
    private int coins;
    private Date lastLogin;

    public User(){
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public int getCoins() { return coins; }

    public void setCoins(int coins) { this.coins = coins; }

    public Date getLastLogin() { return lastLogin; }

    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }
}