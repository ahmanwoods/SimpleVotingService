import { Outlet, Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppNavbar from './components/AppNavBar.js';
import { 
  BrowserRouter,
  Routes,
  Route
 } from "react-router-dom";
import Landing from "./components/Landing"
import Home from "./components/Home"  
import Login from "./components/Login"
import QuestionList from "./components/QuestionList"
import Logout from "./components/Logout"
import useUser from './components/useUser';

export default function App() {

  const { user, setUser } = useUser();

  if(!user) {
    return (
      <div className="wrapper">
      <AppNavbar user={user} setUser={setUser}/>
      <Routes>
          <Route path="/" element={<Login setUser={setUser} />}/>
      </Routes>
      <Outlet />
    </div>     
    )
  }

  return (
    <div className="wrapper">
      <AppNavbar user={user} setUser={setUser}/>
      <Routes>
        <Route path="/" element={<QuestionList user={user} setUser={setUser}/>}>
        </Route>
      </Routes>
      <Outlet />
    </div>
  );
}