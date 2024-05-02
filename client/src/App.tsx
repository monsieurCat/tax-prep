import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Grid, GridContainer } from '@trussworks/react-uswds';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import CreateAccount from './pages/CreateAccount';
import { PersonalForm } from "./pages/PersonalForm"
import LoginFailure from './components/LoginFailure';
import FilingStatus from './pages/FilingStatus';
import W2 from './pages/W2';
import Income1099 from './pages/Income1099';
import Deductions from './pages/Deductions';
import Review from './pages/Review';
import Breakdown from './pages/Breakdown';
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
        <Route path="/login-failure" element={<LoginFailure />} />
        <Route path="/filing-status" element={<FilingStatus />} />
        <Route path="/w2" element={<W2 />} />
        <Route path="/income1099" element={<Income1099 />} />
        <Route path="/deductions" element={<Deductions />} />
        <Route path="/review" element={<Review />} />
        <Route path="/breakdown" element={<Breakdown />} />
      </Routes>
    </Router>

  );
}

export default App;

