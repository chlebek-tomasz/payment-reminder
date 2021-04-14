import classes from "./Navbar.module.css";
import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { Context } from "../../Context/Context";
const Navbar = (props) => {
  const context = useContext(Context);
  return (
    <div className={classes.Navbar}>
      <ul>
        {context.user ? (
          <li>
            <Link to="/dashboard">Dashbord</Link>
          </li>
        ) : null}
      </ul>
    </div>
  );
};
export default Navbar;
