import { BrowserRouter, Route, Routes } from 'react-router-dom'

import './App.css'
import Nav from './components/nav/Nav'
import Pokemon_m from './components/Pokemon_m/Pokemon_m'
import Collection from './components/collection/Collection'
import { createContext } from "react";

export interface AuthContextType{
  userId: number,
  username: string,
  setUsername: (username: string) => void,
  role: "unauthenticated" | "USER" | "ADMIN",
  setRole: (role: "unauthenticated" | "USER" | "ADMIN") => void
}

export const authContext = createContext<AuthContextType | null>(null);

function App() {

  return (
    <>
    <Collection/>
    </>
  )
}

export default App
/**
 * <>
      <BrowserRouter>
      <Nav />
      
      <Routes>
        <Route path="/" element />
        <Route path='/login' element />
        <Route path='/logout' element />
        <Route path='/pokemon-management' element={<Pokemon_m/>} />
        <Route path='/user-management' element />
        
      </Routes>

      </BrowserRouter>
    </>
 */
