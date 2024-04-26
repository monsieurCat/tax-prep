import React from 'react';
import { Button } from '@trussworks/react-uswds';

const LandingPage: React.FC = () => {
  return (
    <div className="landing-page">
      <header className="header">
        <h1>Welcome to Our Tax Preparation Service</h1>
      </header>
      <section className="hero">
        
        <p>File your taxes!</p>
      </section>
     
      <section className="cta">
        <Button type="button">Get Started</Button>
      </section>
    </div>
  );
};

export default LandingPage;
