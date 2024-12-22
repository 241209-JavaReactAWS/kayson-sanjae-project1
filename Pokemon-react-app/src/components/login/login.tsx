import 'bootstrap/dist/css/bootstrap.min.css';
import './login.css';
import axios from 'axios';
import { SyntheticEvent, useContext, useEffect, useState } from 'react';
import { authContext } from '../../App';
import { User } from '../../interfaces/user';


function Login() {
    const auth = useContext(authContext)

    const [username, setUsername] = useState<string>('')
    const [password, setPassword] = useState<string>('')

    let login = () => {
        console.log("Login clicked!")

        // So we can do some validation to make sure the username and password fields are actually filled out
        if (!username){
            alert("Please enter a username")
            return;
        }

        if(!password){
            console.log("password input: " + password)
            alert("Please enter a password")
            return;
        }

        axios.post("http://localhost:8080/users/login", 
            {username, password},
            {withCredentials: true}
        ).then((res) => {
            console.log(res.data)
            auth?.setUsername(res.data.username)
            auth?.setRole(res.data.role)
        }).catch((err) => {
            console.log("Cannot login: "+err)
        })
    }



  return (
    <div className='listContainer'>
        <form id="loginForm">
        <div className="mb-3">
            <label htmlFor="usernName" className="form-label">User Name</label>
            <input type="text" className="form-control" id="usernName" 
             onChange={(e:SyntheticEvent) => { setUsername((e.target as HTMLInputElement).value)}}
            />
        </div>

        <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input type="password" className="form-control" id="password" 
             onChange={(e:SyntheticEvent) => { setPassword((e.target as HTMLInputElement).value)}}
            />
        </div>

        <button type="submit" className="btn btn-primary" onClick={ login}>Login</button> <span> </span>
        <button type="button" className="btn btn-primary">Register</button>
    </form>
    </div>
    
  )
}

export default Login