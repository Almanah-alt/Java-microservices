
import {combineReducers} from 'redux'


import reparierReducer from "./reparierReducer";
import finishedDevicesReducer from "./finishedDevicesReducer";
import centerReducer from "./centerReducer";
import deviceReducer from "./deviceReducer";

export default combineReducers({
    reparierReducer: reparierReducer,
    finishedDevicesReducer:finishedDevicesReducer,
    centerReducer:centerReducer,
    deviceReducer:deviceReducer,
})