import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';

import Home from './pages/Home';
import Contact from './pages/Contact';
import Blogs from './pages/Blogs';
import FantasyTeam from './pages/FantasyTeam';
import Footer from './components/Footer';
import PlayerDetail from './components/PlayerDetail';
import PlayerListByCountry from './pages/PlayerListByCountry';

function App() {
  return (
    <Router>
      <Navbar />
      <div className="container mx-auto p-4">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/blogs" element={<Blogs />} />
          <Route path="/fantasy" element={<FantasyTeam />} />
          <Route path="/team" element={<PlayerListByCountry />} />
          <Route path="/player/:name" element={<PlayerDetail />} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;
