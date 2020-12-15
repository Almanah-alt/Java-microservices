import {SIGN_OUT,SIGN_IN,SIGN_UP} from './types'
import axios from 'axios'

export const signUp = (user) => dispatch =>{
    axios.post('/api/user/signUp', user)
        .then(res => {
            console.log("Response: ", res, res.data)
            dispatch({
                type: SIGN_UP
            });
            setTimeout(() =>{
                dispatch({
                    type: SIGN_UP,
                })
            }, 1000)
        })
        .catch(err => console.log(err))
};

export const signIn = (user) => dispatch =>{
    axios.post('/auth', user)
        .then(res => {
            console.log("here!", res.headers);

            localStorage.setItem('access_token', res.headers.authorization);
            dispatch({
                type: SIGN_IN,
                payload: res.headers.authorization
            });
        })
        .catch(err => console.log(err))
};

export const signOut = () => dispatch =>{
    dispatch({
        type: SIGN_OUT
    })
};