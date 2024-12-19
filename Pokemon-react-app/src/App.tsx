import { BrowserRouter, Route, Routes } from 'react-router-dom'

import './App.css'
import Nav from './components/nav/Nav'
import Pokemon_m from './components/Pokemon_m/Pokemon_m'

function App() {

  return (
    <>
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
  )
}

export default App
