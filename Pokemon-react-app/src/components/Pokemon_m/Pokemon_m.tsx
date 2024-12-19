import './Pokemon_m.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { SyntheticEvent, useEffect, useState } from 'react';
import {Pokemon} from "../../interfaces/pokemon";


function Pokemon_m() {
  const [pokemonList, setPokemonList] = useState<Pokemon[] | null>([]);


  const getPokemonList = ()=>{
    axios.get('http://localhost:8080/api/pokemons')
      .then((response) => {
        setPokemonList(response.data);
      })
      .catch((err) => {
        console.log("Cannot get Pokemon List due to "+err.message);
      })
  }
  
  const handlePokemonDelete = (event : SyntheticEvent<HTMLButtonElement>) => {
    const pokemonId = event.currentTarget.getAttribute('data-id')
    axios.delete(`http://localhost:8080/api/pokemons/id/${pokemonId}`)
    .then((response)=>{
      if(response.status === 200){
        setPokemonList((prevList) => prevList?.filter((p) => p.pokemonId !== Number(pokemonId)) || null);
      }else if(response.status === 404){
        alert("Cannot find the Pokemon in the database.")
      }else{
        alert("Unexpected error to delete the Pokemon.")
      }
    })
    .catch((response)=>{
      alert("Unexpected error to delete the Pokemon: "+ response)
    })
  }

  useEffect(() => {getPokemonList()}, []); 
  
  return (
    <div className="listContainer">
        <div id="pokemonList" className="list">
            <h3>Pokemon List(can edit Pokemon name, price, type and etc below)</h3>
            <table className="table table-hover">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Type1</th>
                    <th scope="col">Type2</th>
                    <th scope="col">Cost</th>
                    <th scope="col">Image</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </tr>
                </thead>
    
                <tbody>
                {
                  pokemonList?.map(
                    (pokemon)=>(
                      <tr key={pokemon.pokemonId}>
                      <th scope="row">{pokemon.pokemonId}</th>
                      <td>{pokemon.name}</td>
                      <td>{pokemon.type1}</td>
                      <td>{pokemon.type2}</td>
                      <td>{pokemon.cost}</td>
                      <td>TBD</td>
                      <th><button data-id={pokemon.pokemonId}>Edit</button></th>
                      <th><button data-id={pokemon.pokemonId} onClick={handlePokemonDelete}>Delete</button></th>
                      </tr>
                    )
                  )
                }    
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default Pokemon_m