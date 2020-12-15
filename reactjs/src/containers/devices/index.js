import React, { useState, useEffect } from 'react';
import {Button, Form, Input, Table} from 'antd';
import './rep.css';
import '../../App.css'
import {getDevices, addDevice} from "../../store/actions/deviceActions";
import {finishDevice} from "../../store/actions/finishedDevices";
import {getReparierByName, getRepariers} from "../../store/actions/user";
import Header from "../../components/header";
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

const onMount = props => () => {
    props.getDevices();
    props.getRepariers()

};


function Devices(props) {
    useEffect(onMount(props), []);
    const[isOpen, setOpen] = useState(false)
    const {devices} = props.deviceReducer;
    let rep = null;

    const [repId, setRepId] = useState({
        repId: null,
    });

    const openForm = () =>{
        if(isOpen){
            setOpen(false)
        }else {
            setOpen(true)
        }
    };

    const handleChange = e =>{
        setRepId({...repId, [e.target.name]: e.target.value})
    };

    const columns = [
        {
            title: 'Device owner',
            dataIndex: 'deviceOwnerName',
        },
        {
            title: 'Device owner phone',
            dataIndex: 'deviceOwnerPhone',
        },{
            title: 'Explanation',
            dataIndex: 'explanation',
        },{
            title: 'Manufacturer',
            dataIndex: 'manufacturer',
        },{
            title: 'Year',
            dataIndex: 'year',
        },
        {
            title: 'Status',
            dataIndex: 'status',
        },
        {
            title: 'Action',
            dataIndex: 'id',
            key: 'id',
            render: (id) => (
                    <div>
                        <Button onClick={openForm}> Finish</Button>
                        <Button onClick={() => props.finishDevice(id,repId.repId)}> Save</Button>
                        {console.log("iddddd", repId.repId)}
                    </div>
            ),
        },
    ];

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
                label="Repairer id"
                name="repId"
                rules={[{ required: true, message: 'Please input repairer id' }]}
            >
                <Input name="repId" value={repId.repId} onChange={handleChange}/>
            </Form.Item>
        </Form>

    );

    const deviceList = devices.map(item => (
        {
            deviceOwnerName: item.deviceOwnerName,
            deviceOwnerPhone: item.deviceOwnerPhone,
            explanation: item.explanation,
            manufacturer: item.manufacturer,
            status: item.status,
            year: item.year,
            id: item.id

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



    return (
        <div>
            <Header/>
            <div className="container-inner container">
                <div>
                    <Table
                        rowSelection={{
                            ...rowSelection,
                        }}
                        columns={columns}
                        dataSource={deviceList}/>
                    {console.log("pppppp", deviceList)}
                </div>
                {isOpen ? deviceForm: noForm}
            </div>
        </div>
    );
}

const mapStateToProps = state =>({
    deviceReducer: state.deviceReducer,
    finishedDevicesReducer:state.finishedDevicesReducer,
    reparierReducer:state.reparierReducer,
});

const mapDispatchToProps = {
    getDevices,
    addDevice,
    finishDevice,
    getRepariers,
    getReparierByName,
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(Devices))