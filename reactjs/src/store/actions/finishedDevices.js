import {EDIT_FINISHED_DEVICES, GET_FINISHED_DEVICES, POST_FINISHED_DEVICES} from './types'
import axios from 'axios'

export const getDevices = () => dispatch =>{
    axios.get('/api/finishedDevices')
        .then(res => {
            console.log("Response: ", res, res.data)
            dispatch({
                type: GET_FINISHED_DEVICES,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const takeDevices = (id) => dispatch =>{
    console.log("Responsennnnnn: ","booooooo");
    axios.post(`/api/finishedDevices/${id}`)
        .then(res => {

            dispatch({
                type: EDIT_FINISHED_DEVICES,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const finishDevice = (deviceId, repId) => dispatch =>{
    axios.post(`/api/finishedDevices/${deviceId}/${repId}`)
        .then(res => {
            console.log("Response: ", res, res.data)
            dispatch({
                type: POST_FINISHED_DEVICES,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};