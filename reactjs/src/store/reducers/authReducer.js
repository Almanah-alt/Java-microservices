
import {SIGN_IN, SIGN_OUT, SIGN_UP} from '../actions/types'
import jwt_decode from 'jwt-decode'
import axios from 'axios'

const initialState = {
    isAuth: false,
    currentUserId: null,
    signUpSuc: false,
    username: ''
};

export default function (state=initialState, action){
    console.log(action);
    if (action.type === SIGN_UP) {
        return {
            ...state,
            signUpSuc: !state.signUpSuc
        }
    } else  if (action.type === SIGN_IN) {
        const payload = jwt_decode(action.payload);
        console.log("REducer payload" + action.payload);
        if (payload.exp < new Date().getTime / 1000){
            return {
                ...state,
                isAuth: false,
                currentUserId: null
            }
        }
        axios.defaults.headers.common.Authorization = `Bearer ${action.payload}`;
        return {
            ...state,
            isAuth: true,
            username: payload.sub
        }
    } else  if (action.type === SIGN_OUT) {
        localStorage.removeItem('access_token');
        delete axios.defaults.headers.common["Authorization"];
        return {
            ...state,
            isAuth: false,
            currentUserId: null
        }
    }
    else {
        return state;
    }
}
