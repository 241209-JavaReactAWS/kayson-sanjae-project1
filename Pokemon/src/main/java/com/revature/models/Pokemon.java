package com.revature.models;

import jakarta.persistence.*;

@Entity
@Table(name="pokemons")
public class Pokemon{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pokemonId;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private PokemonType type1;
    @Enumerated(EnumType.STRING)
    private PokemonType type2;
    private String imgUrl;
    private int cost;

    public Pokemon(){}

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

    public String getImgUrl() { return imgUrl; }

    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public int getCost() { return cost; }

    public void setCost(int cost) { this.cost = cost; }
}