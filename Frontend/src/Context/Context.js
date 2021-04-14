import React, { useState } from "react";

export const Context = React.createContext({
  backendLink: "http://localhost:8080",
  user: {
    token: "",
    user: {
      email: "",
      firstName: "",
      id: 0,
      lastName: "",
    },
  },
  setUser: () => {},
});

const ContextProvider = (props) => {
  const [backendLink] = useState("http://localhost:8080");
  const [user, setUser] = useState(null);
  return (
    <Context.Provider
      value={{ backendLink: backendLink, user: user, setUser: setUser }}
    >
      {props.children}
    </Context.Provider>
  );
};
export default ContextProvider;
