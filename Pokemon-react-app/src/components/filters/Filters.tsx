const types: string[] = ["NORMAL", "FIRE", "WATER", "ELECTRIC", "GRASS", "ICE", "FIGHTING", 
                        "POISON", "GROUND", "FLYING", "PSYCHIC", "BUG", "ROCK", "GHOST", 
                        "DRAGON", "DARK", "STEEL", "FAIRY", "STELLAR"]

function Filters() {

  const filterTypes = types.map(type => (
    <option 
    className="types" 
    value={type} 
    label={type}>type
    </option>
  ));

  return (
    <div>
        <input type="text" placeholder="Type Pokemon&#39;s Name"/>
        <button id="toggle-dropdown">Type</button>
        <select name="types" id="type-filter" multiple>
            {filterTypes}
        </select>
        <input type="radio" id="acquired-checkbox" name="checkbox" value="acquired"/>
        <input type="radio" id="unacquired-checkbox" name="checkbox" value="unacquired"/>
    </div>
  )
}

export default Filters