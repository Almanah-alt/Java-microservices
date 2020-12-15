import React, { useEffect } from 'react';
import {Button, Table} from 'antd';
import './rep.css';
import '../../App.css'
import {getDevices, takeDevices} from "../../store/actions/finishedDevices";
import Header from "../../components/header";
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

const onMount = props => () => {
    props.getDevices()
};


function Devices(props) {

    const {finishedDevices} = props.finishedDevicesReducer;

    const finishedDevicesList = finishedDevices.map(item => (
       {
           date: item.date,
           isTaken: String(item.isTaken),
           deviceId: item.deviceId,
           deviceOwnerName: String(item.deviceOwnerName),
           deviceOwnerPhone: item.deviceOwnerPhone,
           explanation: item.explanation,
           manufacturer: item.manufacturer,
           year: item.year,
           name: item.name,
           phone: item.phone,
           id: item.id
       }
    ));


    const columns = [
        {
            title: 'Date',
            dataIndex: 'date',
            render: text => <a>{text}</a>
        },
        {
            title: 'Is taken',
            dataIndex: 'isTaken',
        },{
            title: 'Device id',
            dataIndex: 'deviceId',
        },
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
            title: 'Repairer name',
            dataIndex: 'name',
            render: text => <a>{text}</a>,
        },
        {
            title: 'Reparier phone',
            dataIndex: 'phone',
            key: "phone",
        },{
            title: 'Action',
            dataIndex: 'id',
            key: 'id',
            render: (id) => (
                <Button type="primary" onClick={() => props.takeDevices(id)}> Take</Button>
            ),
        },

    ];
    const rowSelection = {
        onChange: (selectedRowKeys, selectedRows) => {
            console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        },
        getCheckboxProps: record => ({
            disabled: record.name === 'Disabled User', // Column configuration not to be checked
            name: record.name,
        }),
    };

    useEffect(onMount(props), []);



    return (
        <div>
            <Header/>
        <div className="container-inner container">
            <div>
                <Table
                    rowSelection={{
                        type: 'checkbox',
                        ...rowSelection,
                    }}
                    columns={columns}
                    dataSource={finishedDevicesList}/>
            </div>
        </div>
        </div>
    );
}

const mapStateToProps = state =>({
    finishedDevicesReducer: state.finishedDevicesReducer,
});

const mapDispatchToProps = {
    getDevices,
    takeDevices,
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(Devices))