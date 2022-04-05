import React, { useState } from 'react';
import PropTypes from 'prop-types';
import './css/Login.css';

async function loginUser(credentials) {
  var response = await fetch('/users/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(credentials)
  })

  if (response.status == 404) 
  {
    response = await fetch('/users/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(credentials)
    })
  } 

  var data = await response.json()

  if (response.status != 200 && response.status != 201)
  {
    return null;
  }
  else
  {
    return data;
  }
}

export default function Login({ setUser }) {
  const [username, setUserName] = useState();
  const [loginerror, setLoginError] = useState(false);

  const handleSubmit = async e => {
    e.preventDefault();
    const user = await loginUser({username});
    if (user) 
    {
      setUser(user);
    }
    else
    {
      setLoginError(true)
    }

  }

  return (
    <div className = "landing-wrapper">
      <h1>Simple Voting Service</h1>
      <div className = "login-wrapper">
        <form onSubmit={handleSubmit}>
          <div className="input-group mb-3">
            <input type="text" className="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon2" onChange={e => setUserName(e.target.value)}></input>
            <div className="input-group-append">
              <button className="btn btn-primary" type="submit">Login</button>
            </div>
          </div>
        </form>
        { loginerror === true && <p>There was an error signing in</p>}
      </div>
    </div>
  );
}

Login.propTypes = {
  setUser: PropTypes.func.isRequired
}