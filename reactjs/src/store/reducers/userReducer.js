
import {DELETE_REPS, GET_REPARIERS, GET_REPARIERS_BY_NAME, ADD_REPAIRER, ADD_CENTERS} from '../actions/types'


const initialState = {
    repariers: [],
    reparier: {},
};

export default function (state=initialState, action){
    if (action.type === GET_REPARIERS) {
        return {
            ...state,
            repariers: action.payload
        }
    }else  if (action.type === ADD_REPAIRER) {
        return {
            ...state,
            repariers: [...state.repariers, action.payload]
        }
    }else if (action.type === DELETE_REPS) {
        return {
            ...state,
            repariers: removeById(state.repariers, action.payload)
        }
    }else if (action.type === GET_REPARIERS_BY_NAME) {
        console.log("zdeeees" + action.payload);
        return {
            ...state,
            reparier: action.payload
        }
    } else {
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



