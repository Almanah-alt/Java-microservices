
import {combineReducers} from 'redux'


import userReducer from "./userReducer";
import finishedDevicesReducer from "./finishedDevicesReducer";
import centerReducer from "./centerReducer";
import deviceReducer from "./deviceReducer";
import authReducer from "./authReducer";

export default combineReducers({
    userReducer: userReducer,
    authReducer:authReducer,
    finishedDevicesReducer:finishedDevicesReducer,
    centerReducer:centerReducer,
    deviceReducer:deviceReducer,
})