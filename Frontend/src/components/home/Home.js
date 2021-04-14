import React, { useContext } from "react";
import axios from "axios";
import Signin from "./Signin/Signin";
import Signup from "./Signup/Signup";
import { Context } from "../../Context/Context";
import classes from "./Home.module.css";
const Home = (props) => {
  const context = useContext(Context);
  const signin = (email, password) => {
    axios
      .post(`${context.backendLink}/api/auth/signin`, {
        email: email,
        password: password,
      })
      .then((respone) => {
        context.setUser(respone);
        props.history.push('/dashboard');
      })
      .catch((error) => {
        console.log(error);
        console.log(context.user)
      });
  };

  const signup = (name, lastname, email, password) => {
    console.log(email);
    axios
      .post(`${context.backendLink}/api/auth/signup`, {
        firstName: name,
        lastname: lastname,
        email: email,
        password: password,
      })
      .then((respone) => {
        console.log(respone);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className={classes.Home}>
      <Signin signin={signin} />
      <Signup signup={signup} />
    </div>
  );
};
export default Home;
