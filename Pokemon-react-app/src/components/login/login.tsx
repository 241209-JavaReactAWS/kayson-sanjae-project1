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
            console.log("Logined username and role:")
            console.log(auth?.username)
            console.log(auth?.role)
            alert(`Login successfully. Username: ${res.data.username}   Role: ${res.data.role}`)
            setUsername("")
            setPassword("")
        }).catch((err) => {
            console.log("Cannot login: "+err)
        })
    }

    let normalUserRegister = () => {

        if (!username){
            alert("Please enter a username")
            return;
        }

        if(!password){
            console.log("password input: " + password)
            alert("Please enter a password")
            return;
        }

        let userRegisData:User = { 
            userId: null,
            username: username,
            password: password,
            role: "USER",
            coins: 0,
            lastLogin: null
        }

        axios.post("http://localhost:8080/users/register", 
            userRegisData,
            {withCredentials: true}
        ).then((res) => {
            console.log("Registered username and role:")
            console.log(res.data.username)
            console.log(res.data.role)
            alert(`Registration successfully. Username: ${res.data.username}   Role: ${res.data.role}`)
            setUsername("")
            setPassword("")
        }).catch((err) => {
            console.log("Cannot login: "+err)
        })
    }

    useEffect(()=>{
        console.log("Current username and role:")
        console.log(auth?.username)
        console.log(auth?.role)
    }, [])



  return (
    <div className='listContainer'>
        <form id="loginForm">
        <div className="mb-3">
            <label htmlFor="usernName" className="form-label">User Name</label>
            <input type="text" className="form-control" id="usernName" value={username}
             onChange={(e:SyntheticEvent) => { setUsername((e.target as HTMLInputElement).value)}}
            />
        </div>

        <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input type="password" className="form-control" id="password" value={password}
             onChange={(e:SyntheticEvent) => { setPassword((e.target as HTMLInputElement).value)}}
            />
        </div>

        <button type="button" className="btn btn-primary" onClick={ login}>Login</button> <span> </span>
        <button type="button" className="btn btn-primary" onClick={ normalUserRegister}>Register</button>
    </form>
    </div>
    
  )
}

export default Login