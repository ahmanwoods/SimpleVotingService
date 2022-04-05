import { Outlet, Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppNavbar from './components/AppNavBar.js';
import { 
  BrowserRouter,
  Routes,
  Route
 } from "react-router-dom";
import Login from "./components/Login"
import Vote from "./components/Vote"
import useUser from './components/useUser';

export default function App() {

  const { user, setUser } = useUser();

  if(!user) {
    return (
      <div className="wrapper">
      <AppNavbar user={user} setUser={setUser}/>
      <Routes>
          <Route exact path="/" element={<Login setUser={setUser} />}/>
      </Routes>
      <Outlet />
    </div>     
    )
  }

  return (
    <div className="wrapper">
      <AppNavbar user={user} setUser={setUser}/>
      <Routes>
        <Route path="/" element={<Vote user={user} setUser={setUser}/>}>
        </Route>
      </Routes>
      <Outlet />
    </div>
  );
}