import './user_m.css'
import { User } from '../../interfaces/user';
import { SyntheticEvent, useEffect, useState } from 'react';
import axios from 'axios';


function User_m() {
    const [userList, setUserList] = useState<User[] | null>([])
    const [showModal,setShowMal] = useState<boolean>(false)
    const [isEditing, setIsEditing] = useState<boolean>(false);
    const [formData, setFormData] = useState<User>(
        { userId: null,
        username: "",
        password: "",
        role: "",
        coins: 0,
        lastLogin: null
    })

    const getUserList = ()=>{
        axios.get("http://localhost:8080/users")
        .then((res)=>setUserList(res.data))
        .catch((err)=>console.log("Cannot get user list due to " + err.message))
    }

    // Delete a Pokemon
  const handlePokemonDeleteButton = (event: SyntheticEvent<HTMLButtonElement>) => {
    const userId = event.currentTarget.getAttribute('data-id');
    axios
      .delete(`http://localhost:8080/api/pokemons/id/${pokemonId}`)
      .then((response) => {
        if (response.status === 200) {
          setPokemonList((prevList) => prevList?.filter((p) => p.pokemonId !== Number(pokemonId)) || null);
        } else if (response.status === 404) {
          alert('Cannot find the Pokemon in the database.');
        } else {
          alert('Unexpected error while deleting the Pokemon.');
        }
      })
      .catch((err) => {
        alert('Unexpected error while deleting the Pokemon: ' + err.message);
      });
  };

    useEffect(()=>getUserList(), []);

  return (
    <div className="listContainer">
            
        <div id="userList" className="list">
            <h3>User Management(can edit user name and role below)</h3>
            <table className="table table-hover">
          <thead>
            <tr>
              <th scope="col">UserID</th>
              <th scope="col">User Name</th>
              <th scope="col">Role</th>
              <th scope="col">Coin</th>
              <th scope="col">Last Login</th>
              <th scope="col">Add</th>
              <th scope="col">Edit</th>
              <th scope="col">Delete</th>
            </tr>
          </thead>

          <tbody>
            {userList?.map((user) => (
              <tr key={user.userId}>
                <th scope="row">{user.userId}</th>
                <td>{user.username}</td>
                <td>{user.role}</td>
                <td>{user.coins}</td>
                <td>{user.lastLogin.toString()}</td>
                <td>
                  <button className="btn btn-success" onClick={handleAdd}>
                    Add
                  </button>
                </td>
                <td>
                  <button className="btn btn-warning" data-id={pokemon.pokemonId} onClick={handleEdit}>
                    Edit
                  </button>
                </td>
                <td>
                  <button className="btn btn-danger" data-id={user.userId} onClick={handlePokemonDeleteButton}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
    </div>
  )
}

export default User_m