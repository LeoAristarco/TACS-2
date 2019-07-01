import React from 'react'
import { render } from 'react-dom'
import history from 'helpers/history'
import {
    Router as Router,
} from 'react-router-dom'
import './index.css';
import App from 'views/app'

import 'bootstrap/dist/css/bootstrap.min.css';
import $ from 'jquery';
import Popper from 'popper.js';
import 'bootstrap/dist/js/bootstrap.bundle.min';

render(
    <div>
        <Router history={history}>
            <App history={history}/>
        </Router>
    </div>,
    document.getElementById('app')
);
