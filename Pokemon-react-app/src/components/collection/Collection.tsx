import { useEffect, useState } from "react";
import { usePokemon } from "../../context/PokemonContext";
import { PokemonContextType } from "../../interfaces/pokemonContextType";
import { PokemonProps } from "../../interfaces/pokemonProps";
import CollectionPokemons from "../collection-pokemons/CollectionPokemons";
import Filters from "../filters/Filters";

function Collection() {
  const pokemonContextType: PokemonContextType|null = usePokemon();
  const [userPokemons, setUserPokemons] = useState<PokemonProps[]>([]);

  useEffect(() => {
    if(!pokemonContextType){ return; }
    const {pokemonPropsList} = pokemonContextType;
    setUserPokemons(pokemonPropsList);
  }, [pokemonContextType]);

  return (
    <>
    <Filters/>
    <CollectionPokemons pokemonPropsList = {userPokemons}/>
    </>
  );
}

export default Collection
