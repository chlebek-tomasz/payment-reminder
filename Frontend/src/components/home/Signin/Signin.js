import React, { useState } from "react";
import classes from "./Signin.module.css";
const Signin = (props) => {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();

  const onLogin = (event) => {
    event.preventDefault();
    props.signin(email, password);
  };

  return (
    <div className={classes.Signin}>
      <h3>Zaloguj się</h3>
      <form onSubmit={onLogin}>
        <input
          type="email"
          placeholder="E-mail"
          onChange={(e) => setEmail(e.target.value)}
        ></input>
        <input
          type="password"
          placeholder="Hasło"
          onChange={(e) => setPassword(e.target.value)}
        ></input>
        <button type="submit"> Zaloguj</button>
      </form>
    </div>
  );
};
export default Signin;
