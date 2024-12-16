let filterContainer = document.getElementById("filter-container");
function populateFilters(){
    let fDiv = document.createElement('div')
    fDiv.innerHTML = `
    <input type = 'text' id='search-box' placeholder='Insert Pokemon name'>
    
    <button id='toggle-dropdown'>Types</button>
    
    <select name = 'Type' id='type-filter' multiple>
        <option>NORMAL</option>
        <option>FIRE</option>
        <option>WATER</option>
        <option>ELECTRIC</option>
        <option>GRASS</option>
        <option>ICE</option>
        <option>FIGHTING</option>
        <option>POISON</option>
        <option>GROUND</option>
        <option>FLYING</option>
        <option>PSYCHIC</option>
        <option>BUG</option>
        <option>ROCK</option>
        <option>GHOST</option>
        <option>DRAGON</option>
        <option>DARK</option>
        <option>STEEL</option>
        <option>FAIRY</option>
        <option>STELLAR</option>
    </select>
    
    <input type="radio" id='acquired-checkbox' name='checkbox' value='aquired'>
    <label for='acquired-checkbox'>acquired</label>
    <input type="radio" id='unacquired-checkbox' name='checkbox'>
    <label for='unacquired-checkbox'>unacquired</label>
    `
    
    fDiv.setAttribute('id', 'filters')
    filterContainer.append(fDiv)
}

populateFilters()

const searchBox = document.getElementById('search-box');
const toggleDropdown = document.getElementById('toggle-dropdown');
const typeFilter = document.getElementById('type-filter');
const acquiredCheckbox = document.getElementById('acquired-checkbox');
const unacquiredCheckbox = document.getElementById('unacquired-checkbox');

searchBox.addEventListener('input', ()=>{
})

toggleDropdown.addEventListener('click', () => {
    typeFilter.classList.toggle('visible')
})


acquiredCheckbox.addEventListener('change', ()=>{
    
})

unacquiredCheckbox.addEventListener('change', ()=>{
    
})

let pokemons = [
    { pokemonId: 1, name: "Bulbasaur", owned: false, cost: 10 },
    { pokemonId: 2, name: "Ivysaur", owned: false, cost: 10 },
    { pokemonId: 3, name: "Venusaur", owned: false, cost: 10 },
    { pokemonId: 4, name: "Charmander", owned: false, cost: 10 },
    { pokemonId: 5, name: "Charmeleon", owned: false, cost: 10 },
    { pokemonId: 6, name: "Charizard", owned: false, cost: 10 },
    { pokemonId: 7, name: "Squirtle", owned: false, cost: 10 },
    { pokemonId: 8, name: "Wartortle", owned: false, cost: 10 }
]

let collectionContainer = document.getElementById('collection-container')
function populateUserCollection(){
    
    for(pokemon of pokemons){
        let cDiv = document.createElement('div');
        
        cDiv.innerHTML = `
        <h3 class="center">${pokemon.name}</h3>
        `
        cDiv.setAttribute('class', 'pokemon');
        cDiv.setAttribute('id', `pokemon-${pokemon.pokemonId}`);
        
        collectionContainer.append(cDiv);
    }
    
}

populateUserCollection()