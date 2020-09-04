import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './main/javascript/serviceWorker';

import App from './main/javascript/app';

import './main/javascript/dillo-bot.css';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('react-app')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
