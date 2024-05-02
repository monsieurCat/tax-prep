import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Grid, GridContainer } from '@trussworks/react-uswds';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import CreateAccount from './pages/CreateAccount';
import { PersonalForm } from "./pages/PersonalForm"
import PrivateData from './pages/PrivateData';


function App() {

  
  return (
   
       
       
      <Router>
       <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/create-account" element={<CreateAccount />} />
          <Route path="/personal-form" element={<PersonalForm />} />
          <Route path="/private-data" element={<PrivateData />} />
        </Routes>
      </Router>
      
   
  );
}

export default App;

