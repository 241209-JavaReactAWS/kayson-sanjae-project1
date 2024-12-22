import { FilterPokemonProps } from "../../interfaces/FilterPokemonProps";
import { PokemonProps } from "../../interfaces/pokemonProps";
import Pokemon from "../pokemon/Pokemon";

function CollectionPokemons({filteredPokemons}: FilterPokemonProps) {
  const filteredPokemons1: PokemonProps[] = [
    { id: 25, name: 'Pikachu', type1: 'Electric', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png', cost: 500, variant: 'collection' },
    { id: 4, name: 'Charmander', type1: 'Fire', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png', cost: 600, variant: 'collection' },
    { id: 7, name: 'Squirtle', type1: 'Water', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png', cost: 550, variant: 'collection' },
    { id: 1, name: 'Bulbasaur', type1: 'Grass', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png', cost: 450, variant: 'collection' },
    { id: 2, name: 'Ivysaur', type1: 'Grass', type2: 'Poison', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png', cost: 600, variant: 'collection' },
    { id: 3, name: 'Venusaur', type1: 'Grass', type2: 'Poison', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png', cost: 700, variant: 'collection' },
    { id: 10, name: 'Caterpie', type1: 'Bug', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png', cost: 300, variant: 'collection' },
    { id: 11, name: 'Metapod', type1: 'Bug', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png', cost: 350, variant: 'collection' },
    { id: 12, name: 'Butterfree', type1: 'Bug', type2: 'Flying', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png', cost: 550, variant: 'collection' },
    { id: 13, name: 'Weedle', type1: 'Bug', type2: 'Poison', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png', cost: 300, variant: 'collection' },
  ];

  return (
  <>
    {filteredPokemons1.map((pokemon:PokemonProps) =>
      <Pokemon key={pokemon.id} {...pokemon}/>
    )}
  </>

  
  );
}

export default CollectionPokemons;
