import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Grid, GridContainer } from '@trussworks/react-uswds';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';


function App() {

  
  return (
   
       
       
      <Router>
       <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </Router>
      
   
  );
}

export default App;

