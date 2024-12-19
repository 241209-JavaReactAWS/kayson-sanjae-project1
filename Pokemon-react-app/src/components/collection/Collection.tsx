import axios from "axios";
import Filters from "../filters/Filters";
import { useContext, useState, useEffect } from "react";
import { Pokemon } from "../../interfaces/pokemon";
import { authContext } from "../../App";
import CollectionPokemons from "../collection-pokemons/CollectionPokemons";
import { PokemonProps } from "../pokemon/Pokemon";



function Collection() {
  const auth = useContext(authContext);
  const [acquiredList, setAcquiredList] = useState<Pokemon[]>([]);
  const [unacquiredList, setUnacquiredList] = useState<Pokemon[]>([]);

  useEffect(() => {
    axios.get<Pokemon[]>(
      `http://localhost:8080/users/${auth?.userId}/pokemons?status=acquired`,
    { withCredentials: true }).
    then((res) => {
      setAcquiredList(res.data);
    });
  }, [auth?.userId]);

  useEffect(() => {
    axios.get<Pokemon[]>(
      `http://localhost:8080/users/${auth?.userId}/pokemons?status=unacquired`,
    { withCredentials: true }).
    then((res) => {
      setUnacquiredList(res.data);
    });
  }, [auth?.userId]);

  const aList:PokemonProps[] = acquiredList.map((p) => ({
      id: p.pokemonId,
      name: p.name,
      type1: p.type1,
      type2: p.type2,
      imgUrl: p.imgUrl,
      cost:p.cost,
      owned: true,
      variant: "collection"
  }))

  const bList: PokemonProps[] = unacquiredList.map((p) => ({
      id: p.pokemonId,
      name: p.name,
      type1: p.type1,
      type2: p.type2,
      imgUrl: p.imgUrl,
      cost:p.cost,
      owned: false,
      variant: "collection"
  }))

  const combinedList = [...aList, ...bList]
  const sortedList = combinedList.sort((a,b) => a.id - b.id)

  return (
    <>
    <Filters/>
    <CollectionPokemons pokemonPropsList = {sortedList}/>
    </>
  );
}

export default Collection
