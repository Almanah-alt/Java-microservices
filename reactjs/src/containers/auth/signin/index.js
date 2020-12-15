import React, {useEffect, useState} from 'react';
import './signin.css';
import '../../../App.css'
import { Form, Input, Button, Checkbox } from 'antd';
import {signIn} from "../../../store/actions/authAction";
import { connect } from 'react-redux'
import Header from "../../../components/header";
import {withRouter} from "react-router";
import authReducer from "../../../store/reducers/authReducer";
import {Link} from "react-router-dom";


function SignIn(props) {

    const {signUpSuc} = props.authReducer;

    const [formData, setFormData] = useState({
       username: '',
       password: ''
    });



    const handleChange = e =>{
        setFormData({...formData, [e.target.name]: e.target.value})
    };

    const layout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 8 },
    };
    const tailLayout = {
        wrapperCol: { offset: 8, span: 8 },
    };
    const onFinish = values => {
        console.log('Success:', values);
    };

    const onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
    };
    useEffect(() => {
    }, [props.authReducer.signUpSuc, props.authReducer.isAuth]);

    const openRep = () => {
        props.history.push('/api/user')
    };

    const handleSend = () =>{
        props.signIn(formData);
        if(props.authReducer.isAuth) {
            openRep()
        }

    };



    return (
       <div>
           <Header/>
           <div className="container-inner container">
               <Form
                   {...layout}
                   name="basic"
                   initialValues={{ remember: true }}
                   onFinish={onFinish}
                   onFinishFailed={onFinishFailed}
               >
                   <Form.Item
                       label="Username"
                       name="username"
                       rules={[{ required: true, message: 'Please input your username!' }]}
                   >
                       <Input name="username" value={formData.username} onChange={handleChange}/>
                   </Form.Item>

                   <Form.Item
                       label="Password"
                       name="password"
                       rules={[{ required: true, message: 'Please input your password!' }]}
                   >
                       <Input.Password name="password" value={formData.password} onChange={handleChange}/>
                   </Form.Item>

                   <Form.Item {...tailLayout}>
                       <Link to={'/api/user'}>
                           <Button type="primary" htmlType="submit" onClick={handleSend}>
                             Submit
                           </Button>
                       </Link>
                   </Form.Item>
               </Form>
           </div>
       </div>
    );
}
const mapStateToProps = state => ({
    authReducer:state.authReducer
});

const mapDispatchToProps = {
    signIn,

};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(SignIn))