import React, { ReactElement, useState } from 'react';
import ReactDOM from 'react-dom';
import './register.css';
import '../layout/util.css';
import 'mini.css';

import Header from '../layout/header';

export interface IRegisterProps {}

function Register({}: IRegisterProps): ReactElement {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  function handleInputChange(e: React.ChangeEvent<HTMLInputElement>) {
    const target = e.target;
    if (target) {
      switch (target.name) {
        case 'username':
          setUsername(target.value);
          break;
        case 'password':
          setPassword(target.value);
          break;
      }
    }
  }

  return (
    <>
      <Header />
      <div className="container">
        <div className="row center">
          <div className="input-group" id="regsiter-input">
            <div className="row center">
              <label htmlFor="username">Username</label>
              <input
                type="text"
                name="username"
                value={username}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="row center">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                name="password"
                value={password}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="row">
              <div className="spacer"></div>
              <input type="button" name="register" value="登録" required />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

const react = document.getElementById('react');
ReactDOM.render(<Register />, react);
