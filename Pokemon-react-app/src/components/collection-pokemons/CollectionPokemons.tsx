import Pokemon, { PokemonProps } from "../pokemon/Pokemon";
interface PokemonPropsList{
  pokemonPropsList: PokemonProps[]
}
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
