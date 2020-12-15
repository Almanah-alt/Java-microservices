
import {ADD_CENTERS, ASSIGN_TO_CENTER, GET_CENTERS, GET_CENTER_REPAIRERS, REMOVE_REP_FROM_CENTER} from '../actions/types'


const initialState = {
    centers: [],
    centerReps: [],
};

export default function (state=initialState, action){
    if (action.type === GET_CENTERS) {
        return {
            ...state,
            centers: action.payload
        }
    }else  if (action.type === ADD_CENTERS) {
        return {
            ...state,
            centers: [...state.centers, action.payload]
        }
    }else if (action.type === ASSIGN_TO_CENTER){
        return {
            ...state,
            centerReps: [...state.centerReps, action.payload]
        }
    }else if (action.type === GET_CENTER_REPAIRERS){
        return {
            ...state,
            centerReps: action.payload
        }
    }else if (action.type === REMOVE_REP_FROM_CENTER){
        return {
            ...state,
            centerReps: removeById(state.centerReps, action.payload)
        }
    }
    else {
        return state;
    }
}


function removeById(list, id) {
    for(let i = list.length - 1; i >= 0; i--) {
        if(list[i].id === id) {
            list.splice(i, 1);
            break
        }
    }
    return [...list]
}
