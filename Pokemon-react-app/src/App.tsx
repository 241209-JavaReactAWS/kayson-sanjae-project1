import { BrowserRouter, Route, Routes } from 'react-router-dom'

import './App.css'
import Nav from './components/nav/Nav'
import {User} from './interfaces/user'
import Pokemon_m from './components/Pokemon_m/Pokemon_m'
import Login from './components/login/login'
import { createContext, useEffect, useState } from 'react'
import axios from 'axios'
import Collection from "./components/collection/Collection";

export interface AuthContextType{
  userId : number,
  username: string,
  setUsername: (username: string) => void,
  role: "unauthenticated" | "USER" | "ADMIN",
  setRole: (role: "unauthenticated" | "USER" | "ADMIN") => void
}
export const authContext = createContext<AuthContextType | null>(null);



function App() {
  const [userId, setUserId] = useState<number>(-1);
  const [username, setUsername] = useState<string>('')
  const [role, setRole] = useState<"unauthenticated" | "USER" | "ADMIN">('unauthenticated')
  
  // get current user session info from backend
  useEffect(()=>{
    axios.get<User>('http://localhost:8080/users', {withCredentials: true})
    .then((res) => {
      setUserId(res.data.userId)
      setUsername(res.data.username)
      setRole(res.data.role as "USER" | "ADMIN")
    })
    .catch((err) => {
      console.log("Guest user: " + err)
      setUserId(-1)
      setUsername('')
      setRole('unauthenticated')
    })
  }, [])

  return (
    <authContext.Provider value={
      {
        userId,
        username,
        setUsername,
        role,
        setRole
      }
    }>
      <BrowserRouter>
      <Nav />
      
      <Routes>
        <Route path="/" element />
        <Route path='/login-register' element={<Login />} />
        <Route path='/logout' element />
        <Route path='/pokemon-management' element={<Pokemon_m/>} />
        <Route path='/user-management' element />
        <Route path='/collection' element={<Collection/>}/>
        <Route path='/shop' element={<Collection/>}/>
      </Routes>

      </BrowserRouter>
    </ authContext.Provider>
  )
}

export default App