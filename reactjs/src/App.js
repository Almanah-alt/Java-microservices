import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route } from "react-router-dom";
import {store} from './store'
import {Provider} from 'react-redux';
import User from './containers/users'
import FinDev from './containers/finishedDevices'
import Centers from './containers/centers'
import Devices from './containers/devices'
import Device from './components/addDevice'
import SignIn from "./containers/auth/signin";
import SignUp from "./containers/auth/signup";

function App() {
  return (
      <Provider store={store}>
        <div className="App">
            <Router>
                <Route path="/auth" exact component={SignIn}/>
                <Route path="/api/user/signUp" exact component={SignUp}/>
                <Route path="/api/user" exact component={User}/>
                <Route path="/api/finishedDevices" exact component={FinDev}/>
                <Route path="/api/center" exact component={Centers}/>
                <Route path="/api/device" exact component={Devices}/>
                <Route path="/api/newDevice" exact component={Device}/>
            </Router>
        </div>
      </Provider>
  );
}

export default App;
