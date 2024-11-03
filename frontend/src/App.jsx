import { Outlet } from "react-router-dom";
import Navbar from "./CustomComponents/layout/Navbar";
import Footer from "./CustomComponents/layout/Footer";

function App() {
  return (
    <div>
      <div className="max-w-7xl mx-auto">
        <Navbar />
        <div className="main">
          <Outlet />
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default App;
