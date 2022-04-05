import { useState } from 'react';
import Cookies from 'js-cookie';

export default function useUser() {
  const getUser = () => {
    const userIdString = localStorage.getItem('user');
    const storedUser = JSON.parse(userIdString);
    return storedUser
  };

  const saveUser = user => {
    localStorage.setItem('user', JSON.stringify(user));
    setUser(user);
  }

const [user, setUser] = useState(getUser());

return{
  setUser: saveUser,
  user
}

}