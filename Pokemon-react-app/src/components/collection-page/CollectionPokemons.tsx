import { PokemonPropsList } from "../../interfaces/PokemonPropsList";
import Pokemon from "../pokemon/Pokemon";

function CollectionPokemons({pokemonPropsList}: PokemonPropsList) {
  return (
  <>
    {pokemonPropsList.map((pokemon) =>
      <Pokemon key={pokemon.id} {...pokemon}/>
    )}
  </>
  );
}

export default CollectionPokemons;
