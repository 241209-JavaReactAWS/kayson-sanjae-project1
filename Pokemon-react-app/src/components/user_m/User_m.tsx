import './user_m.css'

function User_m() {
  return (
    <div id="listContainer">
            
        <div id="userList" className="list">
            <h3>User Management(can edit user name and role below)</h3>
            <table className="table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Role</th>
                </tr>
                </thead>
    
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Admin1</td>
                    <td>admin</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Nomal user name1</td>
                    <td>normal</td>
                </tr>
                
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default User_m