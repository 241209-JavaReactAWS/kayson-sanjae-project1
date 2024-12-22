import { FilterPokemonProps } from "../../interfaces/FilterPokemonProps";
import { PokemonProps } from "../../interfaces/pokemonProps";
import Pokemon from "../pokemon/Pokemon";

function CollectionPokemons({filteredPokemons}: FilterPokemonProps) {
  const filteredPokemons1: PokemonProps[] = [
    { id: 25, name: 'Pikachu', type1: 'Electric', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png', cost: 500, variant: 'collection' },
    { id: 4, name: 'Charmander', type1: 'Fire', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png', cost: 600, variant: 'collection' },
    { id: 7, name: 'Squirtle', type1: 'Water', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png', cost: 550, variant: 'collection' },
    { id: 1, name: 'Bulbasaur', type1: 'Grass', type2: '', imgUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png', cost: 450, variant: 'collection' },
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
