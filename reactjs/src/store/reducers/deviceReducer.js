
import {ADD_DEVICES, GET_DEVICES} from '../actions/types'


const initialState = {
    devices: [],
};

export default function (state=initialState, action){
    if (action.type === GET_DEVICES) {
        return {
            ...state,
            devices: action.payload
        }
    }else  if (action.type === ADD_DEVICES) {
        return {
            ...state,
            devices: [...state.devices, action.payload]
        }
    }
    else {
        return state;
    }
}
