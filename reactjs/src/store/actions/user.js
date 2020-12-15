import {ADD_REPAIRER, GET_REPARIERS, GET_REPARIERS_BY_NAME} from './types'
import axios from 'axios'

export const getRepariers = () => dispatch =>{
    axios.get('/api/user')
        .then(res => {
            dispatch({
                type: GET_REPARIERS,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const getReparierByName = name => dispatch =>{
    console.log("heeeeey" + name);
    axios.get(`/api/user/username/${name}`)
        .then(res => {
            dispatch({
                type: GET_REPARIERS_BY_NAME,
                payload: res.data
            })
        })
        .catch(err => console.log(err.msg))
};

export const deleteRepariers = (id) => dispatch =>{
    axios.delete(`/api/user/${id}`)
        .then(res => {
            dispatch({
                type: GET_REPARIERS,
                payload: id
            })
        })
        .catch(err => console.log(err))
};
//
// export const addRepairers = (user) => dispatch =>{
//     axios.post(`/api/repairer/${username}/${password}/${name}/${phone}/${idOfRoom}/${price}`)
//         .then(res => {
//             dispatch({
//                 type: ADD_REPAIRER,
//                 payload: res.data
//             })
//         })
//         .catch(err => console.log(err))
// };