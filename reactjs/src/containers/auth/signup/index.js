import './signup.css';
import '../../../App.css'
import React, { useState } from 'react';
import {Button, Form, Input, Select} from "antd";
import Header from "../../../components/header";
import {signUp} from "../../../store/actions/authAction";
import {connect} from "react-redux";
const {Option} = Select


function SignUp(props) {
    const [formData, setFormData] = useState({
        name: '',
        idOfRoom: null,
        price: null,
        phone: '',
        username: '',
        password: '',
        role: null
    });

    const handleChange = e =>{
        setFormData({...formData, [e.target.name]: e.target.value})
    };
    const roleChange = value => {
        if (value === "USER") {
            setFormData({...formData, role: 0})
        }
        else if (value === "REPAIRER") {
            setFormData({...formData, role: 2})
        }
    }

    const handleSave = () =>{
        props.signUp(formData)
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

    return (
        <div>
            <Header/>
            <div className="container container-inner">
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
                        label="Price"
                        name="price"
                        rules={[{ required: true, message: 'Please input your price!' }]}
                    >
                        <Input name="price" value={formData.price} onChange={handleChange}/>
                    </Form.Item>
                    <Form.Item
                        label="Phone"
                        name="phone"
                        rules={[{ required: true, message: 'Please input your phone' }]}
                    >
                        <Input name="phone" value={formData.phone} onChange={handleChange}/>
                    </Form.Item>
                    <Form.Item
                        label="Name"
                        name="name"
                        rules={[{ required: true, message: 'Please input your name!' }]}
                    >
                        <Input name="name" value={formData.name} onChange={handleChange}/>
                    </Form.Item>
                    <Form.Item
                        label="Room id"
                        name="idOfRoom"
                        rules={[{ required: true, message: 'Please input your idOfRoom!' }]}
                    >
                        <Input name="idOfRoom" value={formData.idOfRoom} onChange={handleChange}/>
                    </Form.Item>
                    <Form.Item
                        label="Role"
                        name="role"
                        rules={[{ required: true, message: 'Please choose your role!' }]}
                    >
                        {/*< name="role" value={formData.role} onChange={roleChange}/>*/}
                        <Select onChange={roleChange}>
                            <option value="USER">User</option>
                            <option value="REPAIRER">Repairer</option>
                        </Select>
                    </Form.Item>
                    <Form.Item
                        label="Password"
                        name="password"
                        rules={[{ required: true, message: 'Please input your password!' }]}
                    >
                        <Input.Password name="password" value={formData.password} onChange={handleChange}/>
                    </Form.Item>

                    <Form.Item {...tailLayout}>
                        <Button type="primary" htmlType="submit" onClick={handleSave}>
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    );
}

const mapStateToProps = state => ({

});

const mapDispatchToProps = {
    signUp,

};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SignUp)
