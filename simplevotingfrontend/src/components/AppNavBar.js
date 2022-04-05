import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem} from 'reactstrap';
import {Link, NavLink} from 'react-router-dom';
import useUser from './useUser';

export default function AppNavbar({ user, setUser }) {
    console.log(user)
        if (user) 
        {
            return (<Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">SimpleVotingService</NavbarBrand>
            <NavItem style={{color: 'white'}} onClick={() => setUser(null)}>Logout {user.username}</NavItem>
        </Navbar>);
        }
        
        return (<Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">SimpleVotingService</NavbarBrand>
        </Navbar>);
}