import { FilterPokemonProps } from "../../interfaces/FilterPokemonProps";
import { PokemonProps } from "../../interfaces/pokemonProps";
import Pokemon from "../pokemon/Pokemon";

function CollectionPokemons({filteredPokemons}: FilterPokemonProps) {
  return (
    <>
      {filteredPokemons.map((pokemon:PokemonProps) =>
        <Pokemon key={pokemon.id} {...pokemon}/>
      )}
    </>
  );
}

export default CollectionPokemons;
