import './css/Landing.css'
import { Outlet, Link } from "react-router-dom";

export default function Landing() {
  return (
    <div className = "landing-wrapper">
       <h1>Simple Voting Service</h1>
       <Outlet />
      </div>
  );
}