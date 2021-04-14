import Navbar from "../components/navbar/Navbar"
import React from 'react'
import { Route } from "react-router"
import Home from "../components/home/Home"
import User from "../components/user/User"
const Layout = (props) => {
    return <React.Fragment>
        <Navbar/>
        <Route path="/" exact component={Home}/>
        <Route path="/dashboard" exact component={User}/>
    </React.Fragment>
}
export default Layout;