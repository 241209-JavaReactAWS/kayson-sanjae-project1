package com.revature.models;

public class Pokemon{
    private int pokemonId;
    private String name;
    private PokemonType type1;
    private PokemonType type2;

    public Pokemon(){

    }

    public Pokemon(String name, PokemonType type1, PokemonType type2) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonType getType1() {
        return type1;
    }

    public void setType1(PokemonType type1) {
        this.type1 = type1;
    }

    public PokemonType getType2() {
        return type2;
    }

    public void setType2(PokemonType type2) {
        this.type2 = type2;
    }
}