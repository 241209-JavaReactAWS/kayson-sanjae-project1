package com.revature.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userShopId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pokemon1_id")
    private Pokemon pokemon1;

    @ManyToOne
    @JoinColumn(name = "pokemon2_id")
    private Pokemon pokemon2;

    @ManyToOne
    @JoinColumn(name = "pokemon3_id")
    private Pokemon pokemon3;

    @ManyToOne
    @JoinColumn(name = "pokemon4_id")
    private Pokemon pokemon4;

    @ManyToOne
    @JoinColumn(name = "pokemon5_id")
    private Pokemon pokemon5;

    public UserShop() {}

    public int getUserShopId() {
        return userShopId;
    }

    public void setUserShopId(int userShopId) {
        this.userShopId = userShopId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<Pokemon> getAllPokemon(){
        Set<Pokemon> pokemons = new HashSet<>();
        pokemons.add(pokemon1);
        pokemons.add(pokemon2);
        pokemons.add(pokemon3);
        pokemons.add(pokemon4);
        pokemons.add(pokemon5);
        return pokemons;
    }

    public void setAllPokemon(Set<Pokemon> pokemonSet){
        List<Pokemon> pokemons = new ArrayList<>(pokemonSet);
        this.pokemon1 = pokemons.get(0);
        this.pokemon2 = pokemons.get(1);
        this.pokemon3 = pokemons.get(2);
        this.pokemon4 = pokemons.get(3);
        this.pokemon5 = pokemons.get(4);
    }
}
