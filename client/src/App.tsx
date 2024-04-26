import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Grid, GridContainer } from '@trussworks/react-uswds';
import Navbar from './components/Navbar';
import Home from './pages/Home';


function App() {

  
  return (
    <GridContainer className="container">
       <Navbar />
       <Grid row>
      <Router>
       
        <Routes>
          <Route path="/" element={<Home />} />
        </Routes>
      </Router>
      </Grid>
    </GridContainer>
  );
}

export default App;

