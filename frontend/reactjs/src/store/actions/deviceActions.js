import {ADD_DEVICES, GET_DEVICES} from './types'
import axios from 'axios'

export const getDevices = () => dispatch =>{
    axios.get('http://localhost:8083/api/device')
        .then(res => {
            console.log("Response: ", res, res.data)
            dispatch({
                type: GET_DEVICES,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const addDevice = (owner, phone, explanation, manuf, year) => dispatch =>{
    axios.post(`http://localhost:8083/api/device/${owner}/${phone}/${explanation}/${manuf}/${year}`)
        .then(res => {
            dispatch({
                type: ADD_DEVICES,
                payload: res.data
            });
        })
        .catch(err => console.log(err))
};