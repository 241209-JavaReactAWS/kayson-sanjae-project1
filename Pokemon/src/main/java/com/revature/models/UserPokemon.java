package com.revature.models;

import jakarta.persistence.*;

@Entity
@Table(name = "userPokemons")
public class UserPokemon{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userPokemonId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    public UserPokemon() {}

    public int getUserPokemonId() { return userPokemonId; }

    public void setUserPokemonId(int userPokemonId) { this.userPokemonId = userPokemonId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Pokemon getPokemon() { return pokemon;}

    public void setPokemon(Pokemon pokemon) { this.pokemon = pokemon; }
}