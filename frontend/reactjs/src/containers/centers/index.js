import React, { useState, useEffect } from 'react';
import {Button, Form, Input, Table} from 'antd';
import './rep.css';
import '../../App.css'
import {getCenters, addCenters, assignToCenter, getCenterRepairers, removeRepFromCenter} from "../../store/actions/centerActions";
import Header from "../../components/header";
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

const onMount = props => () => {
    props.getCenters()
        props.getCenterRepairers()
};

function Centers(props) {

    useEffect(onMount(props), []);

    const {centers} = props.centerReducer;
    const {centerReps} = props.centerReducer;
    const [isOpen, setOpen] = useState(false);
    const [isOpenAddRep, setOpenAddRep] = useState(false);
    const [formData, setFormData] = useState({
        id: null,
        location: '',
        name: '',
    });
    const [repId, setRepId] = useState({
        repId: null,
    });
    const handleChange = e =>{
        setFormData({...formData, [e.target.name]: e.target.value})
    };

    const handleChangeRep = e =>{
        setRepId({...repId, [e.target.name]: e.target.value})
    };

    const handleSave = () =>{
        props.addCenters(formData.name, formData.location)
    };



    const openFormAddRep = () =>{
        if(isOpenAddRep){
            setOpenAddRep(false)
        }else {
            setOpenAddRep(true)
        }
    };

    const columns = [
        {
            title: 'Id',
            dataIndex: 'id',
        },
        {
            title: 'Location',
            dataIndex: 'location',
            render: text => <a>{text}</a>,
        },
        {
            title: 'Name',
            dataIndex: 'name',

        },
        {
            title: 'Action',
            dataIndex: 'id',
            key: 'id',
            render: (id) => (
                <div>
                    <Button onClick={openFormAddRep}>Add new Repairer</Button>
                    <Button onClick={() => props.assignToCenter(id,repId.repId)}> Save</Button>
                </div>
            ),
        },

    ];


    const centerRepColumns = [
        {
            title: 'Id',
            dataIndex: 'id',
        },
        {
            title: 'Center name',
            dataIndex: 'centerName',
            render: text => <a>{text}</a>,
        },
        {
            title: 'Location',
            dataIndex: 'centerLocation',

        },{
            title: 'Repairer name',
            dataIndex: 'repairerName',

        },{
            title: 'Room id',
            dataIndex: 'idOfRoom',

        },{
            title: 'Price',
            dataIndex: 'price',

        },{
            title: 'Phone',
            dataIndex: 'phone',

        },{
            title: 'Repairer id',
            dataIndex: 'repId',

        },

    ];

    const centerList = centers.map(item => (
        {
            id:item.id,
            name: item.name,
            location: item.location
        }
    ));

    const centerRepList = centerReps.map(item => (
        {
            id:item.id,
            repairerName: item.repairerName,
            centerLocation: item.centerLocation,
            repId: item.repId,
            phone: item.phone,
            price: item.price,
            idOfRoom: item.idOfRoom,
            centerName: item.centerName
        }
    ));


    const rowSelection = {
        onChange: (selectedRowKeys, selectedRows) => {
            console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        },
        getCheckboxProps: record => ({
            disabled: record.name === 'Disabled User', // Column configuration not to be checked
            name: record.name,
        }),
    };

    const [selectionType, setSelectionType] = useState('checkbox');



    const openForm = () =>{
        if(isOpen){
            setOpen(false)
        }else {
            setOpen(true)
        }
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

    const noForm =(
        <span/>
    );

    const deviceForm =(
        <Form
            {...layout}
            name="basic"
            initialValues={{ remember: true }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Location"
                name="location"
                rules={[{ required: true, message: 'Please input your location!' }]}
            >
                <Input name="location" value={formData.location} onChange={handleChange}/>
            </Form.Item>
            <Form.Item
                label="Name"
                name="name"
                rules={[{ required: true, message: 'Please input your name' }]}
            >
                <Input name="name" value={formData.name} onChange={handleChange}/>
            </Form.Item>

            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit" onClick={handleSave}>
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );

    const repForm =(
        <Form
            {...layout}
            name="basic"
            initialValues={{ remember: true }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Repairer id"
                name="repId"
                rules={[{ required: true, message: 'Please input repairer id' }]}
            >
                <Input name="repId" value={repId.repId} onChange={handleChangeRep}/>
            </Form.Item>
        </Form>

    );
    const isLoggedIn =(
        <div>
            <Button type="primary" onClick={openForm}>Add new Center</Button>
            <div>
                {isOpen ? deviceForm: noForm}
            </div>
            <div>
                {isOpenAddRep ? repForm: noForm}
            </div>
        </div>
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
                        dataSource={centerList}/>
                </div>

                {isLoggedIn}

                <div>
                    <Table
                    rowSelection={{
                        type: selectionType,
                        ...rowSelection,
                    }}
                    columns={centerRepColumns}
                    dataSource={centerRepList}/>
                </div>
            </div>
        </div>
    );
}

const mapStateToProps = state =>({
    centerReducer: state.centerReducer,
});

const mapDispatchToProps = {
    getCenters,
    addCenters,
    assignToCenter,
    getCenterRepairers,
    removeRepFromCenter
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(Centers))



