import {useState } from "react";

const types: string[] = [
  "NORMAL", "FIRE", "WATER", "ELECTRIC", "GRASS", "ICE", "FIGHTING",
  "POISON", "GROUND", "FLYING", "PSYCHIC", "BUG", "ROCK", "GHOST",
  "DRAGON", "DARK", "STEEL", "FAIRY", "STELLAR"
];

function Filters() {
  const [selected, setSelected] = useState<string | null>(null);

  const filterTypes = types.map(type => (
    <option className="types" value={type} label={type} key={type}>
      {type}
    </option>
  ));

  return (
    <div>
      <input type="text" placeholder="Type Pokémon's Name" />
      <button id="toggle-dropdown">Type</button>
      <select name="types" id="type-filter" multiple>
        {filterTypes}
      </select>
      <input type="radio" 
             id="acquired-checkbox" 
             name="checkbox" 
             value="acquired"
             checked={selected === "acquired"}
             onClick={()=>
              setSelected(selected === "acquired" ? null : "acquired")
             } 
             />
      <label htmlFor="acquired-checkbox">acquired</label>
      <input type="radio" 
             id="unacquired-checkbox" 
             name="checkbox" 
             value="unacquired"
             checked={selected === "unacquired"}
             onClick={() =>
              setSelected(selected === "unacquired" ? null : "unacquired")
             }
            />
      <label htmlFor="unacquired-checkbox">unacquired</label>
    </div>
  );
}

export default Filters;
