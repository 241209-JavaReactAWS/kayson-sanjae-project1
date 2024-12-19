import { Link } from "react-router-dom"

function Nav() {
  return (
    <nav>
        <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/login-register">Login/Register</ Link></li>
            <li><Link to="/logout">Logout</ Link></li>
            <li><Link to="/pokemon-management">Pokemon Management</ Link></li>
            <li><Link to="/user-management">User Management</ Link></li>
        </ul>
    </nav>
  )
}

export default Nav