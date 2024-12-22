import { BrowserRouter, Route, Routes, useNavigate } from 'react-router-dom'

import './App.css'
import Nav from './components/nav/Nav'
import {User} from './interfaces/user'
import Pokemon_m from './components/Pokemon_m/Pokemon_m'
import Login from './components/login/login'
import { createContext, useContext, useEffect, useState } from 'react'
import axios from 'axios'
import User_m from './components/user_m/User_m'
import Collection from './components/collection/Collection'
import Logout from './components/login/logout'


export interface AuthContextType{
  userId : number | null,
  username: string,
  setUsername: (username: string) => void,
  role: "unauthenticated" | "USER" | "ADMIN",
  setRole: (role: "unauthenticated" | "USER" | "ADMIN") => void
  coins: number,
  setCoins: (coins: number) => void 
}

export const authContext = createContext<AuthContextType | null>(null);

function App() {
  // const navigate = useNavigate();
  const [userId, setUserId] = useState<number | null>(-1);
  const [username, setUsername] = useState<string>('');
  const [role, setRole] = useState<"unauthenticated" | "USER" | "ADMIN">('unauthenticated');
  const [coins, setCoins] = useState<number>(0);

  /*
  useEffect(() => {
    if(!useContext(authContext)){
      navigate("/login");
    }
  }, [useContext(authContext), navigate]);
  */

 

  return (
    <authContext.Provider value={
      {
        userId,
        username,
        setUsername,
        role,
        setRole,
        coins,
        setCoins,
      }
    }>
      <BrowserRouter>
      <Nav />
      
      <Routes>
        <Route path="/" element />
        <Route path='/login-register' element={<Login />} />
        <Route path='/pokemon-management' element={<Pokemon_m/>} />
        <Route path='/user-management' element={<User_m />} />
        <Route path='/user-management' element />
        <Route path='/collection' element={<Collection/>}/>
        <Route path='/shop' element={<Collection/>}/>
      </Routes>

      </BrowserRouter>
    </ authContext.Provider>
  )
}

export default App