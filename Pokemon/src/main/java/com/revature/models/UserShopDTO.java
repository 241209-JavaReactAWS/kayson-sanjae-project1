package com.revature.models;

import java.util.Set;

public class UserShopDTO {
    private int userShopId;
    private User user;
    private Set<Pokemon> pokemonSet;

    public UserShopDTO(UserShop userShop){
        this.userShopId = userShop.getUserShopId();
        this.user = userShop.getUser();
        this.pokemonSet = userShop.getAllPokemon();
    }

    public int getUserShopId() { return userShopId; }

    public void setUserShopId(int userShopId) { this.userShopId = userShopId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Set<Pokemon> getPokemonSet() { return pokemonSet; }

    public void setPokemonSet(Set<Pokemon> pokemonSet) { this.pokemonSet = pokemonSet; }
}
