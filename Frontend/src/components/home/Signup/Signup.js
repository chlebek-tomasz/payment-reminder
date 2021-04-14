import React, { useState } from "react";
import classes from "./Signup.module.css";
const Singup = (props) => {
  const [name, setName] = useState();
  const [lastname, setLastname] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();

  const onRegister = (event) => {
    event.preventDefault();
    props.signup(name, lastname, email, password);
  };
  return (
    <div className={classes.Signup}>
      <form onSubmit={onRegister}>
        <h3>Zarejestruj się</h3>
        <input
          type="text"
          placeholder="Imię"
          onChange={(e) => setName(e.target.value)}
        ></input>
        <input
          type="text"
          placeholder="Nazwisko"
          onChange={(e) => setLastname(e.target.value)}
        ></input>

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
        <button type="submit">Zarejestruj</button>
      </form>
    </div>
  );
};
export default Singup;
