import { createContext, useContext, useEffect, useMemo, useState } from "react";
import { authContext } from "../App";
import axios from "axios";
import { Pokemon } from "../interfaces/pokemon";
import { PokemonProps } from "../interfaces/pokemonProps";
import { PokemonContextType } from "../interfaces/pokemonContextType";

const PokemonContext = createContext<PokemonContextType | null>(null);

export const usePokemon = () => {
  return useContext(PokemonContext);
};

export const PokemonProvider = ({ children} : React.PropsWithChildren) => {
  const auth = useContext(authContext);
  const [acquiredList, setAcquiredList] = useState([]);
  const [unacquiredList, setUnacquiredList] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/users/123/pokemons?status=acquired', { withCredentials: true })
      .then((res) => setAcquiredList(res.data));
    axios.get('http://localhost:8080/users/123/pokemons?status=unacquired', { withCredentials: true })
      .then((res) => setUnacquiredList(res.data));
  }, [auth?.userId]);

  const mapPokemonList = (pokemonList:Pokemon[], owned:boolean, variant:string) : PokemonProps[] => pokemonList.map((p) => ({
    id: p.pokemonId,
    name: p.name,
    type1: p.type1,
    type2: p.type2,
    imgUrl: p.imgUrl,
    cost: p.cost,
    owned,
    variant,
  }) as PokemonProps);

  const aList = mapPokemonList(acquiredList, true, 'collection');
  const bList = mapPokemonList(unacquiredList, false, 'collection');

  const pokemonPropsList = useMemo(() => {
    const combinedList = [...aList, ...bList];
    return combinedList.sort((a, b) => a.id - b.id);
  }, [aList, bList]);

  return (
    <PokemonContext.Provider value={{ pokemonPropsList }}>
      {children}
    </PokemonContext.Provider>
  );
};