import React, { useState, useEffect } from 'react';
import {Table, Radio, Divider, Card, Col, Row, Form, Input, Button} from 'antd';
import './rep.css';
import '../../App.css'
import {getRepariers,deleteRepariers, addRepairers} from "../../store/actions/repariers";
import Header from "../../components/header";
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

const { Meta } = Card;
const onMount = props => () => {
    props.getRepariers()
};



function Rep(props) {

    const {repariers} = props.reparierReducer;
    const [isOpen, setOpen] = useState(false)
    const [formData, setFormData] = useState({
        name: '',
        idOfRoom: null,
        price: null,
        phone: '',
        username: '',
        password: ''


    });

    const rowSelection = {
        onChange: (selectedRowKeys, selectedRows) => {
            console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        },
        getCheckboxProps: record => ({
            disabled: record.name === 'Disabled User', // Column configuration not to be checked
            name: record.name,
        }),
    };
    const columns = [
        {
            title: 'Id',
            dataIndex: 'id',
            key: 'id',
            render: text => <a>{text}</a>,
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
            render: text => <a>{text}</a>,
        },
        {
            title: 'Room id',
            dataIndex: 'room',
            key: 'room',

        },
        {
            title: 'Phone',
            dataIndex: 'phone',
            key: "phone",
        },
        {
            title: 'Price',
            dataIndex: 'price',
            key: 'price',
        },
    ];

    const repList = repariers.map(item => (
        {
            name: item.name,
            phone: item.phone,
            price: item.price,
            room: item.idOfRoom,
            id: item.id
        }
    ));

        const [selectionType, setSelectionType] = useState('checkbox');
    useEffect(onMount(props), []);

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

    const noForm =(
        <span/>
    );
    const handleSave = () =>{
        props.addRepairers(formData.username, formData.password, formData.name, formData.phone, formData.idOfRoom, formData.price)
    };

    const handleChange = e =>{
        setFormData({...formData, [e.target.name]: e.target.value})
    };


    const openForm = () =>{
        if(isOpen){
            setOpen(false)
        }else {
            setOpen(true)
        }
    };

    const repairerForm =(
        <Form
            {...layout}
            name="basic"
            initialValues={{ remember: true }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Name"
                name="name"
                rules={[{ required: true, message: 'Please input your location!' }]}
            >
                <Input name="name" value={formData.name} onChange={handleChange}/>
            </Form.Item>
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
                <Input name="password" type="password" value={formData.password} onChange={handleChange}/>
            </Form.Item>
            <Form.Item
                label="Phone"
                name="phone"
                rules={[{ required: true, message: 'Please input your phone' }]}
            >
                <Input name="phone" value={formData.phone} onChange={handleChange}/>
            </Form.Item>
            <Form.Item
                label="Room Id"
                name="idOfRoom"
                rules={[{ required: true, message: 'Please input your phone' }]}
            >
                <Input name="idOfRoom" value={formData.idOfRoom} onChange={handleChange}/>
            </Form.Item>
            <Form.Item
                label="Price"
                name="price"
                rules={[{ required: true, message: 'Please input your phone' }]}
            >
                <Input name="price" value={formData.price} onChange={handleChange}/>
            </Form.Item>

            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit" onClick={handleSave}>
                    Add
                </Button>
            </Form.Item>
        </Form>
    );


    return (
        <div>
            <Header/>
        <div className="container-inner container">
            <div>
                <Table
                    rowSelection={{
                        type: selectionType,
                        ...rowSelection,
                    }}
                    columns={columns}
                    dataSource={repList}/>
            </div>
            <div>
                <div>
                    <Button type="primary" onClick={openForm}>Add new Repairer</Button>
                    {isOpen ? repairerForm: noForm}
                </div>
            </div>
        </div>
        </div>
    );
}

const mapStateToProps = state =>({
    reparierReducer: state.reparierReducer
});

const mapDispatchToProps = {
    getRepariers,
    deleteRepariers,
    addRepairers,
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(Rep))