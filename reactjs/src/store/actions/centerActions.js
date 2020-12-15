import {ADD_CENTERS, GET_CENTERS, ASSIGN_TO_CENTER, GET_CENTER_REPAIRERS, REMOVE_REP_FROM_CENTER} from './types'
import axios from 'axios'

export const getCenters = () => dispatch =>{
    axios.get("/api/center")
        .then(res => {
            console.log("ResponsXSSe: ", res, res.data)
            dispatch({
                type: GET_CENTERS,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const addCenters = (name, location) => dispatch =>{
    console.log("ResponsXSSe: "," addaadaadda")
    axios.post(`/api/center/${name}/${location}`)
        .then(res => {
            dispatch({
                type: ADD_CENTERS,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const assignToCenter = (centerId, repId) => dispatch =>{
    axios.post(`/api/centerRep/${centerId}/${repId}`)
        .then(res => {
            dispatch({
                type: ASSIGN_TO_CENTER,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const getCenterRepairers = () => dispatch =>{
    axios.get('/api/centerRep')
        .then(res => {
            dispatch({
                type: GET_CENTER_REPAIRERS,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const removeRepFromCenter = (id) => dispatch =>{
    axios.post(`/api/centerRep/remove/${id}`)
        .then(res => {
            dispatch({
                type: REMOVE_REP_FROM_CENTER,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

