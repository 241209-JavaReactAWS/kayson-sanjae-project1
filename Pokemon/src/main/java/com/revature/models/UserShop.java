package com.revature.models;

import jakarta.persistence.*;

@Entity
public class UserShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userShopId;
    private int userId;
    @OneToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    private int pokemon1Id;
    private int pokemon2Id;
    private int pokemon3Id;
    private int pokemon4Id;
    private int pokemon5Id;

    @ManyToOne
    @JoinColumn(name = "pokemon1Id", insertable = false, updatable = false)
    private Pokemon pokemon1;

    @ManyToOne
    @JoinColumn(name = "pokemon2Id", insertable = false, updatable = false)
    private Pokemon pokemon2;

    @ManyToOne
    @JoinColumn(name = "pokemon3Id", insertable = false, updatable = false)
    private Pokemon pokemon3;

    @ManyToOne
    @JoinColumn(name = "pokemon4Id", insertable = false, updatable = false)
    private Pokemon pokemon4;

    @ManyToOne
    @JoinColumn(name = "pokemon5Id", insertable = false, updatable = false)
    private Pokemon pokemon5;

    public UserShop() {
    }

    public int getUserShopId() {
        return userShopId;
    }

    public void setUserShopId(int userShopId) {
        this.userShopId = userShopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPokemon1Id() {
        return pokemon1Id;
    }

    public void setPokemon1Id(int pokemon1Id) {
        this.pokemon1Id = pokemon1Id;
    }

    public int getPokemon2Id() {
        return pokemon2Id;
    }

    public void setPokemon2Id(int pokemon2Id) {
        this.pokemon2Id = pokemon2Id;
    }

    public int getPokemon3Id() {
        return pokemon3Id;
    }

    public void setPokemon3Id(int pokemon3Id) {
        this.pokemon3Id = pokemon3Id;
    }

    public int getPokemon4Id() {
        return pokemon4Id;
    }

    public void setPokemon4Id(int pokemon4Id) {
        this.pokemon4Id = pokemon4Id;
    }

    public int getPokemon5Id() {
        return pokemon5Id;
    }

    public void setPokemon5Id(int pokemon5Id) {
        this.pokemon5Id = pokemon5Id;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public Pokemon getPokemon3() {
        return pokemon3;
    }

    public void setPokemon3(Pokemon pokemon3) {
        this.pokemon3 = pokemon3;
    }

    public Pokemon getPokemon4() {
        return pokemon4;
    }

    public void setPokemon4(Pokemon pokemon4) {
        this.pokemon4 = pokemon4;
    }

    public Pokemon getPokemon5() {
        return pokemon5;
    }

    public void setPokemon5(Pokemon pokemon5) {
        this.pokemon5 = pokemon5;
    }
}
