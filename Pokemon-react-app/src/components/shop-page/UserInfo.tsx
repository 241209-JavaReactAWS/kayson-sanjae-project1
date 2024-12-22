import { useContext } from "react";
import { authContext } from "../../App";

function UserInfo() {
    const auth = useContext(authContext);
    
  if(!auth){
    throw new Error("Authentication missing");
  }
  return (
    <>
      <h4 className="user-info">{auth.username}</h4> 
      <h4 className="user-info">{auth.coins}</h4> 
    </>
  )
}

export default UserInfo