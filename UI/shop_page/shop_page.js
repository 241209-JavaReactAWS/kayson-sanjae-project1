testUser = {
    userId: 1,
    name: "Test",
    currency: 25
}

let userInfoContainer = document.getElementById('user-info-container')

function showUserInfo(){
    let uDiv = document.createElement('div');
    uDiv.innerHTML = `
    <h4 class='userInfo'>${testUser.name}</h6>
    <h4 class='userInfo'>${testUser.currency}</h6>
    `
    uDiv.setAttribute('id', `user-${testUser.userId}`)

    userInfoContainer.append(uDiv)
}
showUserInfo()


let pokemons = [
    {
        pokemonId: 1,
        name: "Bulbasaur",
        owned: false,
        cost: 10
    },
    {
        pokemonId: 4,
        name: "Charmander",
        owned: false,
        cost: 0
    },
    {
        pokemonId: 7,
        name: "Squirtle",
        owned: true,
        cost: 0
    }
]

let shopContainer = document.getElementById('shop-container');

function populateDailyPokemons(){
    for(let pokemon of pokemons){
        let pDiv = document.createElement('div');
        
        pDiv.innerHTML = `
        ${!pokemon.owned ? '<p class="top-right">NEW</p>' : ''}
        <h3 class="center">${pokemon.name}</h3>
        <p class="bottom" >Cost: ${pokemon.cost === 0 ? "Free" : pokemon.cost}</p>
        `
        pDiv.setAttribute('class', 'pokemon');
        pDiv.setAttribute('id', `pokemon-${pokemon.pokemonId}`);

        shopContainer.append(pDiv);
    }
}

populateDailyPokemons()
