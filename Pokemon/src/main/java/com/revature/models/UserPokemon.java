package com.revature.models;

import jakarta.persistence.*;

@Entity
@Table(name = "userPokemons")
public class UserPokemon{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userPokemonId;
    private int userId;
    private long pokemon_flags_1_64;
    private long pokemon_flags_65_128;
    private long pokemon_flags_128_151;


    public UserPokemon() {
    }

    public UserPokemon(int userId, long pokemon_flags_1_64, long pokemon_flags_65_128, long pokemon_flags_128_151) {
        this.userId = userId;
        this.pokemon_flags_1_64 = pokemon_flags_1_64;
        this.pokemon_flags_65_128 = pokemon_flags_65_128;
        this.pokemon_flags_128_151 = pokemon_flags_128_151;
    }

    public int getUserPokemonId() {
        return userPokemonId;
    }

    public void setUserPokemonId(int userPokemonId) {
        this.userPokemonId = userPokemonId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getPokemon_flags_1_64() {
        return pokemon_flags_1_64;
    }

    public void setPokemon_flags_1_64(long pokemon_flags_1_64) {
        this.pokemon_flags_1_64 = pokemon_flags_1_64;
    }

    public long getPokemon_flags_65_128() {
        return pokemon_flags_65_128;
    }

    public void setPokemon_flags_65_128(long pokemon_flags_65_128) {
        this.pokemon_flags_65_128 = pokemon_flags_65_128;
    }

    public long getPokemon_flags_128_151() {
        return pokemon_flags_128_151;
    }

    public void setPokemon_flags_128_151(long pokemon_flags_128_151) {
        this.pokemon_flags_128_151 = pokemon_flags_128_151;
    }
}