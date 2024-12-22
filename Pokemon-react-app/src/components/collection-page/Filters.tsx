import axios from "axios";
import { useState } from "react";
import { Pokemon } from "../../interfaces/pokemon";
import { usePokemon } from "../../context/PokemonContext";
import { FilterPokemonProps } from "../../interfaces/FilterPokemonProps";

const types: string[] = [
  "NORMAL", "FIRE", "WATER", "ELECTRIC", "GRASS", "ICE", "FIGHTING",
  "POISON", "GROUND", "FLYING", "PSYCHIC", "BUG", "ROCK", "GHOST",
  "DRAGON", "DARK", "STEEL", "FAIRY", "STELLAR"
];

function Filters({ updateFilteredPokemons }: FilterPokemonProps) {
  const { pokemonPropsList } = usePokemon() || { pokemonPropsList: [] };
  const [query, setQuery] = useState<string>("");
  const [isDropdownVisible, setIsDropdownVisible] = useState<boolean>(false);
  const [selectedTypes, setSelectedTypes] = useState<string[]>([]);
  const [selectedStatus, setSelectedStatus] = useState<string | null>(null);

  const handleKeyDown = async (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      axios.get<Pokemon[]>(`http://localhost:8080/users/pokemons?name=${query}`,
        { withCredentials: true }
      ).then((res) => {
        const fetchedPokemonIds = res.data.map((pokemon: Pokemon) => pokemon.pokemonId);
        const filterPokemonProps = pokemonPropsList.filter((pokemonProp) =>
          fetchedPokemonIds.includes(pokemonProp.id)
        );
        updateFilteredPokemons(filterPokemonProps);
      }).catch(error => console.log("Could not get pokemonProps List from INPUT-FILTER", error))
    }
  };

  const handleFilterChange = () => {
    const selectedTypesArray = selectedTypes.join(",");
    const params: { [key: string]: string } = {};

    if (selectedTypesArray) {
      params.types = selectedTypesArray;
    }

    if (selectedStatus) {
      params.status = selectedStatus;
    }

    axios.get<Pokemon[]>("http://localhost:8080/users/pokemons", {
      params,
      withCredentials: true,
    }).then((res) => {
      const fetchedPokemonIds = res.data.map((pokemon) => pokemon.pokemonId);
      const filteredPokemonProps = pokemonPropsList.filter((pokemonProp) =>
        fetchedPokemonIds.includes(pokemonProp.id)
      );
      updateFilteredPokemons(filteredPokemonProps); // Update filtered Pokémon list
    }).catch((error) => (console.log("Error fetching Pokémon with selected filters", error)))
  };

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedValues = Array.from(e.target.selectedOptions, (option) => option.value);
    setSelectedTypes(selectedValues);
    setQuery("");
    handleFilterChange();
  };

  const handleRadioChange = (status: string) => {
    setSelectedStatus(status === selectedStatus ? null : status);
    setQuery("");
    handleFilterChange();
  }

  const handleDropdownToggle = () => {
    setIsDropdownVisible(prevState => !prevState); // Toggle dropdown visibility
  };

  return (
    <>
      <input
        type="text"
        placeholder="Type Pokémon's Name"
        value={query}
        onChange={(e) => {
          setQuery(e.target.value);
          setSelectedStatus(null);
          setSelectedTypes([]);
        }}
        onKeyDown={handleKeyDown}
      />

      <button id="toggle-dropdown" onClick={handleDropdownToggle}>
        {isDropdownVisible ? "Hide Types" : "Show Types"}
      </button>
      <select name="types" 
              id="type-filter" 
              multiple
              value={selectedTypes}
              onChange={handleSelectChange}>
          {types.map((type) => (
            <option key={type} value={type}>{type}</option>
          ))}
        </select>

      <input
        type="radio"
        id="acquired-checkbox"
        name="checkbox"
        value="acquired"
        checked={selectedStatus === "acquired"}
        onClick={() => handleRadioChange("acquired")}
        readOnly
      />
      <label htmlFor="acquired-checkbox">acquired</label>

      <input
        type="radio"
        id="unacquired-checkbox"
        name="checkbox"
        value="unacquired"
        checked={selectedStatus === "unacquired"}
        onClick={() => handleRadioChange("unacquired")}
        readOnly
      />
      <label htmlFor="unacquired-checkbox">unacquired</label>
    </>
  );
}

export default Filters;
