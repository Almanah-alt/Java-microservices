import React, { useState, useEffect } from 'react';
import {Table, Radio, Divider, Card, Col, Row, Form, Input, Button} from 'antd';
import './rep.css';
import '../../App.css'
import {getRepariers,deleteRepariers} from "../../store/actions/user";
import Header from "../../components/header";
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

const { Meta } = Card;
const onMount = props => () => {
    props.getRepariers()
};



function User(props) {

    const {repariers} = props.userReducer;
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
        </div>
        </div>
    );
}

const mapStateToProps = state =>({
    userReducer: state.userReducer
});

const mapDispatchToProps = {
    getRepariers,
    deleteRepariers,
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(User))