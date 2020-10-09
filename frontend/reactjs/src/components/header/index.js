import React, {useState, useEffect} from 'react';
import '../../App.css'
import './header.css'
import { Menu } from 'antd';
import { MailOutlined, AppstoreOutlined } from '@ant-design/icons';
import { connect } from 'react-redux'
import {Link, withRouter} from 'react-router-dom'



function Header() {

    const [state, setState] = useState({
        current: ""
    });
    const handleClick = e => {
        console.log('click ', e);
        setState({
            current: e.key,
        });
    };




    const loggedInMenu = (
        <Menu onClick={handleClick} selectedKeys={[state.current]} mode="horizontal">
            <Menu.Item key="rep" icon={<MailOutlined />}>
                <Link to={'/api/repariers'}>Repariers</Link>
            </Menu.Item>
            <Menu.Item key="fDev"  icon={<AppstoreOutlined />}>
                <Link to={'/api/finishedDevices'}>Finished devices</Link>
            </Menu.Item>
            <Menu.Item key="devices" >
                <Link to={'/api/devices'}>All devices</Link>
            </Menu.Item>
            <Menu.Item key="centers" >
                <Link to={'/api/center'}>All centers</Link>
            </Menu.Item>
            <Menu.Item key="newDev" >
                <Link to={'/api/newDevice'}>Add device</Link>
            </Menu.Item>
        </Menu>
    );

    return (
        <div className="header-menu">
            {loggedInMenu}

        </div>
    );
}




export default connect(
)(withRouter(Header))
