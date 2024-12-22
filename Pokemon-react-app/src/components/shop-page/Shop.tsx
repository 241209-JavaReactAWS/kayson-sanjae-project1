import { PokemonContextType } from "../../interfaces/pokemonContextType";
import { usePokemon } from "../../context/PokemonContext";
import Pokemon from "../pokemon/Pokemon";
import UserInfo from "./UserInfo";


function Shop() {
  const {pokemonPropsList}: PokemonContextType = usePokemon();
  return (
    <>
      <UserInfo/>
      <h2 id="daily-shop-title">Daily Shop</h2>
      <div>
        {pokemonPropsList.map((p) => (
          <>
          <Pokemon key={p.id} {...p}/>
          <button
            className={`buy-button ${p.owned ? 'disabled' : ''}`}
            disabled={p.owned}>
            BUY
          </button>
          </>
        ))}
      </div>
    </>
  );
}

export default Shop