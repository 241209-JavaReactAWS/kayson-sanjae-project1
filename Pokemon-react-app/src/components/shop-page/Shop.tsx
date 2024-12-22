import { useContext, useEffect, useState } from "react";
import { PokemonContextType } from "../../interfaces/pokemonContextType";
import { PokemonProps } from "../../interfaces/pokemonProps";
import { usePokemon } from "../../context/PokemonContext";
import { authContext } from "../../App";
import Pokemon from "../pokemon/Pokemon";


function Shop() {
  const auth = useContext(authContext);
  const pokemonContextType: PokemonContextType|null = usePokemon();
  const [userPokemons, setUserPokemons] = useState<PokemonProps[]>([]);

  useEffect(() => {
      if(!pokemonContextType){ return; }
      const {pokemonPropsList} = pokemonContextType;
      setUserPokemons(pokemonPropsList);
    }, [pokemonContextType]);

  if(!auth){
    throw new Error("Authentication missing");
  }

  return (
    <>
      <div>
        <h4 className='userInfo'>{auth.username}</h4>
        <h4 className='userInfo'>{auth.coins}</h4>
      </div>
      <h2 id="daily-shop-title">Daily Shop</h2>
      <div>
        {userPokemons.map((p) =>
          <Pokemon key={p.id} {...p}/>
          )}
      </div>
    </>
  );
}

export default Shop