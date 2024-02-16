import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "../src/index.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>{<App></App>}</React.StrictMode>
);