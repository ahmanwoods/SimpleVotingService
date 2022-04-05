import './css/Landing.css'
import { Outlet, Link } from "react-router-dom";

export default function Landing() {

  return (
    <Link to="/login">
      <button type="button" className="btn btn-primary">Login</button>
    </Link>
  );
}