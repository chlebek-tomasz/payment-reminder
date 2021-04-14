import React from "react";
import { BrowserRouter } from "react-router-dom";
import "./App.css";
import ContextProvider from "./Context/Context";
import Layout from "./layout/Layout";

function App() {
  return (
    <ContextProvider>
      <BrowserRouter>
        <div className="App">
          <Layout />
        </div>
      </BrowserRouter>
    </ContextProvider>
  );
}

export default App;
