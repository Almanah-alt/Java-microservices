
import {EDIT_FINISHED_DEVICES, GET_FINISHED_DEVICES, POST_FINISHED_DEVICES} from '../actions/types'


const initialState = {
    finishedDevices: [],
    finishedDevice: {},
};

export default function (state=initialState, action){
    console.log(action);
    if (action.type === GET_FINISHED_DEVICES) {
        return {
            ...state,
            finishedDevices: action.payload
        }
    }else  if (action.type === POST_FINISHED_DEVICES) {
        return {
            ...state,
            finishedDevices: [...state.finishedDevices, action.payload]
        }
    }else  if (action.type === EDIT_FINISHED_DEVICES) {
        return {
            ...state,
            finishedDevice: state.finishedDevices.map(
                (item, i) => i === action.id ? {...item, isTaken: action.payload} : item
            )

        }
    }
    else {
        return state;
    }
}
