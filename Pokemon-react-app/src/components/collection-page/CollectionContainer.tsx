import { useEffect, useState } from "react";
import { usePokemon } from "../../context/PokemonContext";
import { PokemonContextType } from "../../interfaces/pokemonContextType";
import { PokemonProps } from "../../interfaces/pokemonProps";
import CollectionPokemons from "./CollectionPokemons";
import Filters from "./Filters";
import './CollectionContainer.css'

function Collection() {
  const { pokemonPropsList } : PokemonContextType = usePokemon();
  const [filteredPokemons, setFilteredPokemons] = useState<PokemonProps[]>(pokemonPropsList);

  const updateFilteredPokemons = (filtered: PokemonProps[]) => {
    setFilteredPokemons(filtered);
  };

  const filterPokemonProps = {
    filteredPokemons,
    updateFilteredPokemons
  };

  return (
    <div className="collection-container">
    <Filters {...filterPokemonProps}/>
    <CollectionPokemons {...filterPokemonProps}/>
    </div>
  );
}

export default Collection
