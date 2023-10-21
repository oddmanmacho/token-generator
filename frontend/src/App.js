import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

const App = () => {
  const [digits, setDigitsAllowed] = useState('');
  const [token, setToken] = useState('');
  const [status, setTokenStatus] = useState('Not validated');
  const [error, setError] = useState('');

  const generateToken = () => {
    if (!/^\d$|^\d+(,\d+)*$/.test(digits)) {
      setError('Name should contain either a single digit or comma-separated numbers.');
      return;
    }
    axios.get(`http://localhost:8081/api/generate-token?digits=${digits}`)
      .then(response => {
        setToken(response.data);
        setError('');
        setTokenStatus('Not validated');
      })
      .catch(error => {
        console.error('Error fetching token:', error);
        setError('Error fetching token. Please try again later.');
      });
  };

  const validateToken = () => {
    axios.get(`http://localhost:8081/api/validate-token?token=${token}`)
      .then(response => {
        if(response.data) {
          setTokenStatus('Valid');
        } else {
          setTokenStatus('Not valid');
        }
      })
      .catch(error => {
        console.error('Error validating token:', error);
      });
  };

  return (
    <div className="App">
      <h1>Token Generator</h1>
      <div>
        <label>Enter the digits for token: </label>
        <input type="text" value={digits} onChange={e => setDigitsAllowed(e.target.value)} />
        &nbsp;<button onClick={generateToken}>Generate!</button>
      </div>
      {error && <div className="error-message">{error}</div>}
      <div className="table-container">
        <table className="token-table">
          <tbody>
            <tr>
              <td>Token: </td>
              <td><input type="text" value={token} onChange={e => setToken(e.target.value)} /></td>
              <td><button onClick={validateToken}>validate!</button></td>
              <td>{status}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default App;
